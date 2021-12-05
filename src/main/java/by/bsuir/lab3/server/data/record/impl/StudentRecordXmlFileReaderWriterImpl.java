package by.bsuir.lab3.server.data.record.impl;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import by.bsuir.lab3.domain.record.StudentRecord;
import by.bsuir.lab3.domain.record.impl.SerializableStudentRecordImpl;
import by.bsuir.lab3.server.data.ReadWriteException;
import by.bsuir.lab3.server.data.record.StudentRecordReaderWriter;

public class StudentRecordXmlFileReaderWriterImpl implements StudentRecordReaderWriter {
    @Override
    public List<StudentRecord> readFrom(String path) throws ReadWriteException {
        if (path == null) {
            throw new IllegalArgumentException("Path shouldn't be null");
        }

        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(path))) {
            return (List<StudentRecord>) decoder.readObject();
        } catch (FileNotFoundException e) {
            throw new ReadWriteException("File with path " + path + " was not found", e);
        } catch (ClassCastException e) {
            throw new ReadWriteException("File with path " + path + " doesn't contain student records", e);
        }
    }

    @Override
    public void writeTo(List<StudentRecord> records, String path) throws ReadWriteException {
        if ((records == null) || (path == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(path))) {
            List<SerializableStudentRecordImpl> toWrite = new ArrayList<>();
            ReadWriteException aggregatedIllegalArgumentExceptions = new ReadWriteException("Cannot cast some records");

            for (StudentRecord record : records) {
                try {
                    toWrite.add(new SerializableStudentRecordImpl(record));
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
