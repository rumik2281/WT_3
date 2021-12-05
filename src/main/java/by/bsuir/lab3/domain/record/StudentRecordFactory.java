package by.bsuir.lab3.domain.record;

import java.util.Date;

import by.bsuir.lab3.domain.record.impl.ImmutableStudentRecordImpl;
import by.bsuir.lab3.domain.record.impl.SerializableStudentRecordImpl;

public class StudentRecordFactory {
    public static StudentRecord createRecord(int group, String name, String surname, String address, Date dateOfBirth) {
        return new ImmutableStudentRecordImpl(group, name, surname, address, dateOfBirth);
    }

    public static StudentRecord createSerializableRecord(int group, String name, String surname, String address,
            Date dateOfBirth) {
        return new SerializableStudentRecordImpl(group, name, surname, address, dateOfBirth);
    }

    public static StudentRecord createSerializableRecord(StudentRecord record) {
        return createSerializableRecord(record.getGroup(), record.getName(), record.getSurname(), record.getAddress(),
                record.getDateOfBirth());
    }

    private StudentRecordFactory() {
    }
}
