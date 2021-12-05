package by.bsuir.lab3.server.DAO.dataaccess.record;

import java.util.Date;
import java.util.List;

import by.bsuir.lab3.domain.record.StudentRecord;
import by.bsuir.lab3.server.DAO.dataaccess.Dao;
import by.bsuir.lab3.server.DAO.dataaccess.DaoException;

public interface StudentRecordDao extends Dao<StudentRecord> {
    StudentRecord get(String surname, String name) throws DaoException;

    List<StudentRecord> get(int group);

    void delete(String surname, String name) throws DaoException;

    void update(int group, String name, String surname, String address, Date dateOfBirth) throws DaoException;

    void add(int group, String name, String surname, String address, Date dateOfBirth) throws DaoException;
}
