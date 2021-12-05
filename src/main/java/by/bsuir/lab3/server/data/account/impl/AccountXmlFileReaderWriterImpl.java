package by.bsuir.lab3.server.data.account.impl;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.lab3.domain.account.Account;
import by.bsuir.lab3.domain.account.AccountFactory;
import by.bsuir.lab3.server.data.ReadWriteException;
import by.bsuir.lab3.server.data.account.AccountReaderWriter;

public class AccountXmlFileReaderWriterImpl implements AccountReaderWriter {
    @Override
    public List<Account> readFrom(String path) throws ReadWriteException {
        if (path == null) {
            throw new IllegalArgumentException("Path shouldn't be null");
        }

        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(path))) {
            return (List<Account>) decoder.readObject();
        } catch (FileNotFoundException e) {
            throw new ReadWriteException("File with path " + path + " was not found", e);
        } catch (ClassCastException e) {
            throw new ReadWriteException("File with path " + path + " doesn't contain accounts", e);
        }
    }

    @Override
    public void writeTo(List<Account> accounts, String path) throws ReadWriteException {
        if ((accounts == null) || (path == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(path))) {
            List<Account> toWrite = new ArrayList<>();
            ReadWriteException aggregatedIllegalArgumentExceptions = new ReadWriteException(
                    "Cannot cast some accounts");

            for (Account account : accounts) {
                try {
                    toWrite.add(AccountFactory.createSerializableAccount(account));
                } catch (IllegalArgumentException e) {
                    aggregatedIllegalArgumentExceptions.addSuppressed(e);
                }
            }
            encoder.writeObject(toWrite);

            if (aggregatedIllegalArgumentExceptions.getSuppressed().length != 0) {
                throw aggregatedIllegalArgumentExceptions;
            }
        } catch (FileNotFoundException e) {
            throw new ReadWriteException("Cannot find file " + path, e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
