package by.bsuir.lab3.server.model.command.impl.record;

import java.util.ArrayList;
import java.util.Objects;

import by.bsuir.lab3.domain.record.StudentRecord;
import by.bsuir.lab3.server.DAO.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.lab3.server.model.command.Command;
import by.bsuir.lab3.server.model.command.CommandException;

public class GetAllRecordsCommandImpl implements Command {
    private final String splitter;

    private String createRecordStringRepresentation(StudentRecord studentRecord) {
        return studentRecord.getSurname() + splitter + studentRecord.getName() + splitter
                + studentRecord.getDateOfBirth() + splitter + studentRecord.getGroup() + splitter
                + studentRecord.getAddress();
    }

    @Override
    public String[] execute(String[] request) throws CommandException {
        ArrayList<String> result = new ArrayList<>();

        for (StudentRecord student : StudentRecordDaoFactory.getDao().getAll()) {
            result.add(createRecordStringRepresentation(student));
        }

        return result.toArray(new String[0]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        return Objects.equals(splitter, ((GetAllRecordsCommandImpl) o).splitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(splitter);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@splitter: " + splitter;
    }

    public GetAllRecordsCommandImpl() {
        splitter = "\n";
    }
}
