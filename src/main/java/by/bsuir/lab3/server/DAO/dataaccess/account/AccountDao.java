package by.bsuir.lab3.server.DAO.dataaccess.account;

import by.bsuir.lab3.domain.account.Account;
import by.bsuir.lab3.domain.account.UserRole;
import by.bsuir.lab3.server.DAO.dataaccess.Dao;
import by.bsuir.lab3.server.DAO.dataaccess.DaoException;

public interface AccountDao extends Dao<Account> {
    Account get(String username) throws DaoException;

    void delete(String username) throws DaoException;

    void update(String username, String password, UserRole role) throws DaoException;

    void add(String username, String password, UserRole role) throws DaoException;
}
