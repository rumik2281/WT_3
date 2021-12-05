package by.bsuir.lab3.server.DAO.dataaccess.account;

import by.bsuir.lab3.server.DAO.dataaccess.account.impl.AccountDaoImpl;

public class AccountDaoFactory {
    private static final AccountDao accountDao = new AccountDaoImpl();

    public static AccountDao getDao() {
        return accountDao;
    }

    private AccountDaoFactory() {
    }
}
