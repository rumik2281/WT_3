package by.bsuir.lab3.server.view;

import java.io.FileNotFoundException;
import java.io.PrintStream;

import by.bsuir.lab3.domain.console.ConsoleScanner;
import by.bsuir.lab3.domain.logger.Logger;
import by.bsuir.lab3.server.DAO.dataaccess.DaoException;
import by.bsuir.lab3.server.DAO.dataaccess.account.AccountDaoFactory;
import by.bsuir.lab3.server.DAO.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.lab3.server.view.impl.MultithreadSocketListenerImpl;

public class Main {
    private static final String SHUTDOWN_KEYWORD = "shutdown";

    public static void main(String[] args) {
        MultithreadSocketListenerImpl listener;
        Thread listenerThread;

        if (args.length < 3) {
            Logger.log(new ViewException("At least 3 arguments needed: port, account file path and records file path"));
            return;
        }

        if (args.length > 3) {
            try {
                Logger.setErrorStream(new PrintStream(args[3]));
            } catch (FileNotFoundException e) {
                Logger.log(new ViewException("Error setting error stream", e));
                return;
            }
        }

        try {
            AccountDaoFactory.getDao().loadFrom(args[0]);
            StudentRecordDaoFactory.getDao().loadFrom(args[1]);
        } catch (DaoException e) {
            Logger.log(e);
            return;
        }

        try {
            listener = new MultithreadSocketListenerImpl(Integer.parseInt(args[2]));
        } catch (IllegalArgumentException e) {
            Logger.log(e);
            return;
        }

        listenerThread = new Thread(listener);
        listenerThread.start();

        while (!ConsoleScanner.getNonEmptyString().equals(SHUTDOWN_KEYWORD))
            ;

        listener.stopListen();
        listener.waitForStop();
        try {
            listenerThread.join();
        } catch (InterruptedException e) {
            Logger.log(new ViewException("Iterrupted while joining listener thread", e));
        }
    }
}
