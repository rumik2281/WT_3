package by.bsuir.lab3.server.view.impl;

import java.net.Socket;

import by.bsuir.lab3.domain.request.Request;

public class SocketRequest {
    private final Socket socket;

    private final Request request;

    public Socket getSocket() {
        return socket;
    }

    public Request getRequest() {
        return request;
    }

    public SocketRequest(Socket socket, Request request) {
        this.request = request;
        this.socket = socket;
    }
}
