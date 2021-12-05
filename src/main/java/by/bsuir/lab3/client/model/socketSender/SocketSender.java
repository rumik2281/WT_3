package by.bsuir.lab3.client.model.socketSender;

import by.bsuir.lab3.domain.request.Request;
import by.bsuir.lab3.domain.response.Response;

public interface SocketSender {
    Response send(Request request) throws Exception;
}
