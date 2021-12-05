package by.bsuir.lab3.client.view;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;

import by.bsuir.lab3.client.model.socketSender.SocketSender;
import by.bsuir.lab3.client.model.socketSender.SocketSenderFactory;
import by.bsuir.lab3.client.model.splitter.RequestSplitter;
import by.bsuir.lab3.client.view.impl.SerializableRequest;
import by.bsuir.lab3.domain.console.ConsoleScanner;
import by.bsuir.lab3.domain.logger.Logger;
import by.bsuir.lab3.domain.request.RequestCode;
import by.bsuir.lab3.domain.response.Response;
import by.bsuir.lab3.domain.response.ResponseCode;

public class Main {
    private static SocketSender socketSender;
    private static String username;
    private static String password;
    private static Map<ResponseCode, ResponseProcessor> responseProcessors;
    private static final String UNKNOWN_RESPONSE_MESSAGE = "Unknown response";
    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        String request;
        setup(args);

        if (!tryToLogin()) {
            System.out.println("Error while login");
            return;
        }

        do {
            System.out.print("Command: ");
            request = ConsoleScanner.getNonEmptyString();
            if (!request.equals(EXIT_COMMAND)) {
                try {
                    System.out.println(getResponseMessage(sendRequest(request, false)));
                } catch (Exception e) {
                    Logger.log(e);
                    System.out.println("Error executing command");
                }
            }
        } while (!request.equals(EXIT_COMMAND));
    }

    private static void setup(String[] args) {
        InetAddress serverAddress = null;
        int port = 0;
        if (args.length >= 1) {
            try {
                serverAddress = InetAddress.getByName(args[0]);
            } catch (UnknownHostException e) {
                Logger.log(e);
            }
        }
        while (serverAddress == null) {
            System.out.print("Enter server host: ");
            try {
                serverAddress = InetAddress.getByName(ConsoleScanner.getNonEmptyString());
            } catch (UnknownHostException e) {
                Logger.log(e);
            }
        }
        if (args.length >= 2) {
            port = Integer.parseInt(args[1]);
        }
        while ((port < 1024) || (port > 65535)) {
            System.out.println("Enter port number between 1024 and 65535");
            port = ConsoleScanner.getPositiveInt();
        }
        socketSender = SocketSenderFactory.getSocketSender(serverAddress, port);
        if (args.length >= 3) {
            username = args[2].trim();
        } else {
            System.out.print("Enter username: ");
            username = ConsoleScanner.getNonEmptyString();
        }
        if (args.length >= 4) {
            password = args[3].trim();
        }
        if ((password == null) || password.isEmpty()) {
            System.out.print("Enter password: ");
            password = ConsoleScanner.getNonEmptyString();
        }
        if (args.length >= 5) {
            try {
                Logger.setErrorStream(new PrintStream(new FileOutputStream(args[4])));
            } catch (FileNotFoundException e) {
                Logger.log(new Exception("Error creating file log stream, continuing with default", e));
            }
        }
    }

    private static boolean tryToLogin() {
        try {
            Response response = sendRequest(RequestCode.DOES_ACCOUNT_EXIST.toString() + ' ' + username, true);

            if ((ResponseCode.valueOf(response.getResponseCode()) == ResponseCode.SUCCESS)
                    && (response.getResponseContent()[0].equals(Boolean.toString(true)))) {
                response = sendRequest(RequestCode.LOGIN.toString() + ' ' + username + ' ' + password, true);

                return (ResponseCode.valueOf(response.getResponseCode()) == ResponseCode.SUCCESS)
                        && (response.getResponseContent()[0].equals(Boolean.toString(true)));
            } else {
                response = sendRequest(RequestCode.ADD_ACCOUNT.toString() + ' ' + username + ' ' + password, true);

                return ResponseCode.valueOf(response.getResponseCode()) == ResponseCode.SUCCESS;
            }
        } catch (Exception e) {
            Logger.log(e);
            return false;
        }
    }

    private static Response sendRequest(String userRequest, boolean sendAsUnauthorized) throws Exception {
        String[] request = RequestSplitter.split(userRequest.trim());

        return socketSender.send(new SerializableRequest(request[0].toUpperCase(),
                Arrays.copyOfRange(request, 1, request.length), sendAsUnauthorized ? null : username));
    }

    private static String getResponseMessage(Response response) {
        if (responseProcessors == null) {
            responseProcessors = ResponseProcessorFactory.getResponseProcessors();
        }

        try {
            ResponseProcessor processor = responseProcessors.get(ResponseCode.valueOf(response.getResponseCode()));

            if (processor == null) {
                return UNKNOWN_RESPONSE_MESSAGE;
            } else {
                return processor.getResponseMessage(response.getResponseContent());
            }
        } catch (IllegalArgumentException e) {
            return UNKNOWN_RESPONSE_MESSAGE;
        }
    }
}
