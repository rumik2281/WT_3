package by.bsuir.lab3.server.model.command.impl.record;

import by.bsuir.lab3.server.DAO.dataaccess.DaoException;
import by.bsuir.lab3.server.DAO.dataaccess.record.StudentRecordDaoFactory;
import by.bsuir.lab3.server.model.command.Command;
import by.bsuir.lab3.server.model.command.CommandException;

public class UpdateRecordStorageCommandImpl implements Command {
    @Override
    public String[] execute(String[] request) throws CommandException {
        try {
            StudentRecordDaoFactory.getDao().updateInStorage();
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error updating in storage", e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
