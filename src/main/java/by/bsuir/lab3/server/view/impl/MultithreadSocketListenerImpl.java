package by.bsuir.lab3.server.view.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import by.bsuir.lab3.domain.logger.Logger;
import by.bsuir.lab3.domain.request.Request;
import by.bsuir.lab3.server.view.SocketListener;

public class MultithreadSocketListenerImpl implements SocketListener, Runnable {
    private final int port;
    private final BlockingQueue<SocketRequest> requests;
    private volatile boolean shouldRun;
    private Thread processingThread;
    private ServerSocket serverSocket;

    public MultithreadSocketListenerImpl(int port) {
        if ((port < 1024) || (port > 65535)) {
            throw new IllegalArgumentException("Port should be between 1024 and 65535");
        }

        this.port = port;
        requests = new LinkedBlockingQueue<>();
        processingThread = new Thread(new MultithreadSocketRequestProcessorImpl(requests));
    }

    @Override
    public void startListen() {
        Socket clientSocket;
        Request request;
        shouldRun = true;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            Logger.log(new SocketException("Error while creating server socket", e));
            return;
        }

        processingThread.start();

        while (shouldRun) {
            try {
                clientSocket = serverSocket.accept();
                request = (Request) new ObjectInputStream(clientSocket.getInputStream()).readObject();
                if (request != null) {
                    requests.add(new SocketRequest(clientSocket, request));
                }
            } catch (java.net.SocketException e) {
                Logger.log(new SocketException("Socket exception caught, possibly close() call", e));
            } catch (IOException e) {
                Logger.log(new SocketException("Error accepting user socket", e));
            } catch (ClassNotFoundException | ClassCastException e) {
                Logger.log(new SocketException("Illegal object input", e));
            }
        }
    }

    @Override
    public void stopListen() {
        requests.add(new SocketRequest(null, null));
        shouldRun = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            Logger.log(new SocketException("Error closing server socket", e));
        }
    }

    @Override
    public void waitForStop() {
        try {
            processingThread.join();
        } catch (InterruptedException e) {
            Logger.log(new SocketException("Thread was interrupted while joining prosessing thread", e));
        }
    }

    @Override
    public void run() {
        startListen();
    }
}
