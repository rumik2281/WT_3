package by.bsuir.lab3.server.model.command.impl.account;

import by.bsuir.lab3.domain.digest.PasswordDigestException;
import by.bsuir.lab3.domain.digest.PasswordDigestGeneratorFactory;
import by.bsuir.lab3.server.DAO.dataaccess.DaoException;
import by.bsuir.lab3.server.DAO.dataaccess.account.AccountDaoFactory;
import by.bsuir.lab3.server.model.command.Command;
import by.bsuir.lab3.server.model.command.CommandException;
import by.bsuir.lab3.domain.account.UserRole;

public class UpdateAccountCommandImpl implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 3;

    @Override
    public String[] execute(String[] request) throws CommandException {
        if (request == null) {
            throw new IllegalArgumentException("Request shouldn't be null");
        }
        if (request.length < REQUIRED_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            AccountDaoFactory.getDao().update(request[0],
                    PasswordDigestGeneratorFactory.getPasswordDigestGenerator().generate(request[1]),
                    UserRole.valueOf(request[2]));
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error updating account " + request[0], e);
        } catch (PasswordDigestException e) {
            throw new CommandException("Error creating account password digest", e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
