package by.bsuir.lab3.server.DAO.dataaccess.record;

import by.bsuir.lab3.server.DAO.dataaccess.record.impl.StudentRecordDaoImpl;

public class StudentRecordDaoFactory {
    private static final StudentRecordDao dao = new StudentRecordDaoImpl();

    public static StudentRecordDao getDao() {
        return dao;
    }

    private StudentRecordDaoFactory() {
    }
}
