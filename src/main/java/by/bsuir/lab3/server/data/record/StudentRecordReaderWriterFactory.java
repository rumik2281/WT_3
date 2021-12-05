package by.bsuir.lab3.server.data.record;

import by.bsuir.lab3.server.data.record.impl.StudentRecordXmlFileReaderWriterImpl;

public class StudentRecordReaderWriterFactory {
    private static final StudentRecordReaderWriter readerWriter = new StudentRecordXmlFileReaderWriterImpl();

    public static StudentRecordReaderWriter getReaderWriter() {
        return readerWriter;
    }

    private StudentRecordReaderWriterFactory() {
    }
}
