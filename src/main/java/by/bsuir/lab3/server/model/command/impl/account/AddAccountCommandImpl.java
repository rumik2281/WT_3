package by.bsuir.lab3.server.model.command.impl.account;

import by.bsuir.lab3.domain.account.UserRole;
import by.bsuir.lab3.domain.digest.PasswordDigestException;
import by.bsuir.lab3.domain.digest.PasswordDigestGeneratorFactory;
import by.bsuir.lab3.server.DAO.dataaccess.DaoException;
import by.bsuir.lab3.server.DAO.dataaccess.account.AccountDao;
import by.bsuir.lab3.server.DAO.dataaccess.account.AccountDaoFactory;
import by.bsuir.lab3.server.model.command.Command;
import by.bsuir.lab3.server.model.command.CommandException;

public class AddAccountCommandImpl implements Command {
    private static final byte REQUIRED_ARGUMENTS_COUNT = 2;

    @Override
    public String[] execute(String[] request) throws CommandException {
        if (request == null) {
            throw new IllegalArgumentException("Request shouldn't be null");
        }
        if (request.length < REQUIRED_ARGUMENTS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        try {
            AccountDao dao = AccountDaoFactory.getDao();

            dao.add(request[0], PasswordDigestGeneratorFactory.getPasswordDigestGenerator().generate(request[1]),
                    UserRole.USER);
            dao.updateInStorage();
            return null;
        } catch (DaoException e) {
            throw new CommandException("Error adding new account " + request[0], e);
        } catch (PasswordDigestException e) {
            throw new CommandException("Error generating password digest", e);
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
