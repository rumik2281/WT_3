package by.bsuir.lab3.client.model.socketSender;

import java.net.InetAddress;

import by.bsuir.lab3.client.model.socketSender.impl.SocketSenderImpl;

public class SocketSenderFactory {
    public static SocketSender getSocketSender(InetAddress address, int port) {
        return new SocketSenderImpl(address, port);
    }

    private SocketSenderFactory() {
    }
}
