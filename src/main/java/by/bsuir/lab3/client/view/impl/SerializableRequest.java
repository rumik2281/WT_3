package by.bsuir.lab3.client.view.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import by.bsuir.lab3.domain.request.Request;

public class SerializableRequest implements Serializable, Request {
    private String requestCode;
    private String[] requestContent;
    private String request;

    public SerializableRequest(String requestCode, String[] content, String request) {
        if (requestCode == null) {
            throw new IllegalArgumentException("Request code shouldn't be null");
        }

        this.requestCode = requestCode;
        this.request = request;
        if (content == null) {
            requestContent = null;
        } else {
            requestContent = content.clone();
        }
    }

    public SerializableRequest() {
    }

    @Override
    public String getRequestCode() {
        return requestCode;
    }

    @Override
    public String[] getRequestContent() {
        return requestContent;
    }

    @Override
    public String getRequest() {
        return request;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public void setRequestContent(String[] requestContent) {
        this.requestContent = requestContent;
    }

    public void setRequester(String request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        SerializableRequest toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (SerializableRequest) o;
        return Objects.equals(requestCode, toCompare.requestCode)
                && Arrays.equals(requestContent, toCompare.requestContent)
                && Objects.equals(request, toCompare.request);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(requestCode, request) + Arrays.hashCode(requestContent);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@responseCode: " + requestCode + ", responseContent: "
                + Arrays.toString(requestContent) + ", requester: " + request;
    }

}
