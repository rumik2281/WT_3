package by.bsuir.lab3.server.controller.impl;

import by.bsuir.lab3.domain.account.UserRole;
import by.bsuir.lab3.domain.logger.Logger;
import by.bsuir.lab3.domain.request.Request;
import by.bsuir.lab3.domain.response.Response;
import by.bsuir.lab3.domain.response.ResponseCode;
import by.bsuir.lab3.server.DAO.dataaccess.DaoException;
import by.bsuir.lab3.server.DAO.dataaccess.account.AccountDaoFactory;
import by.bsuir.lab3.server.controller.CommandMapFactory;
import by.bsuir.lab3.server.controller.Controller;
import by.bsuir.lab3.server.model.command.Command;
import by.bsuir.lab3.server.model.command.CommandException;
import by.bsuir.lab3.domain.request.RequestCode;

public class ControllerImpl implements Controller {
    @Override
    public Response process(Request request) {
        UserRole role;
        Command command;

        try {
            role = AccountDaoFactory.getDao().get(request.getRequest()).getRole();
        } catch (DaoException | IllegalArgumentException e) {
            role = null;
        }

        try {
            command = CommandMapFactory.getCommands(role).get(RequestCode.valueOf(request.getRequestCode()));
        } catch (IllegalArgumentException | NullPointerException e) {
            command = null;
        }
        if (command == null) {
            return new SerializableResponseImpl(ResponseCode.NO_SUCH_COMMAND.toString(), null);
        }

        try {
            return new SerializableResponseImpl(ResponseCode.SUCCESS.toString(),
                    command.execute(request.getRequestContent()));
        } catch (CommandException e) {
            Logger.log(e);
            return new SerializableResponseImpl(ResponseCode.INTERNAL_FAILURE.toString(), null);
        } catch (IllegalArgumentException e) {
            Logger.log(e);
            return new SerializableResponseImpl(ResponseCode.ILLEGAL_ARGUMENTS.toString(), null);
        }
    }
}
