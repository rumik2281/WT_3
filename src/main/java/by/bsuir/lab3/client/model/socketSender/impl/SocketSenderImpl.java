package by.bsuir.lab3.client.model.socketSender.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import by.bsuir.lab3.client.model.socketSender.SocketSender;
import by.bsuir.lab3.domain.request.Request;
import by.bsuir.lab3.domain.response.Response;

public class SocketSenderImpl implements SocketSender {
    private final InetAddress address;

    private final int port;

    public SocketSenderImpl(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    @Override
    public Response send(Request request) throws Exception {
        try {
            Socket socket = new Socket(address, port);
            new ObjectOutputStream(socket.getOutputStream()).writeObject(request);
            return (Response) new ObjectInputStream(socket.getInputStream()).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new Exception("Transport error", e);
        }
    }

}
