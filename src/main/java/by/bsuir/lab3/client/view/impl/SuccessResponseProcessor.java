package by.bsuir.lab3.client.view.impl;

import java.util.Objects;

import by.bsuir.lab3.client.view.ResponseProcessor;

public class SuccessResponseProcessor implements ResponseProcessor {
    private final String delimiter;
    private final String message;

    @Override
    public String getResponseMessage(String[] response) {
        if (response == null) {
            return message;
        } else {
            return String.join(delimiter, response);
        }
    }

    @Override
    public boolean equals(Object o) {
        SuccessResponseProcessor toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (SuccessResponseProcessor) o;
        return Objects.equals(delimiter, toCompare.delimiter) && Objects.equals(message, toCompare.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(delimiter, message);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@delimiter: " + delimiter + ", successMessage: " + message;
    }

    public SuccessResponseProcessor() {
        delimiter = "\n\n";
        message = "[success]";
    }
}
