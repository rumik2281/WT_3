package by.bsuir.lab3.server.controller;

import by.bsuir.lab3.domain.request.Request;
import by.bsuir.lab3.domain.response.Response;

public interface Controller {
    Response process(Request request);
}
