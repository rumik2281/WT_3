package by.bsuir.lab3.client.view;

import java.util.HashMap;
import java.util.Map;

import by.bsuir.lab3.client.view.impl.*;
import by.bsuir.lab3.domain.response.ResponseCode;

public class ResponseProcessorFactory {
    private static final Map<ResponseCode, ResponseProcessor> processors;

    private ResponseProcessorFactory() {
    }

    static {
        processors = new HashMap<>();
        processors.put(ResponseCode.SUCCESS, new SuccessResponseProcessor());
        processors.put(ResponseCode.NO_SUCH_COMMAND, new NoSuchCommandResponseProcessor());
        processors.put(ResponseCode.INTERNAL_FAILURE, new InternalFailureResponseProcessor());
        processors.put(ResponseCode.ILLEGAL_ARGUMENTS, new IllegalArgumentsResponseProcessor());
    }

    public static Map<ResponseCode, ResponseProcessor> getResponseProcessors() {
        return new HashMap<>(processors);
    }
}
