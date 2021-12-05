package by.bsuir.lab3.server.view;

public interface SocketListener {
    void startListen();

    void stopListen();

    void waitForStop();
}
