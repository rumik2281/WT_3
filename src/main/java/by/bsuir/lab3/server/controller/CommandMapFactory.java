package by.bsuir.lab3.server.controller;

import java.util.HashMap;
import java.util.Map;

import by.bsuir.lab3.domain.account.UserRole;
import by.bsuir.lab3.domain.request.RequestCode;
import by.bsuir.lab3.server.model.command.Command;
import by.bsuir.lab3.server.model.command.impl.account.AddAccountCommandImpl;
import by.bsuir.lab3.server.model.command.impl.account.DoesAccountExistCommandImpl;
import by.bsuir.lab3.server.model.command.impl.account.LoginCommandImpl;
import by.bsuir.lab3.server.model.command.impl.account.UpdateAccountCommandImpl;
import by.bsuir.lab3.server.model.command.impl.account.UpdateAccountStorageCommandImpl;
import by.bsuir.lab3.server.model.command.impl.record.AddRecordCommandImpl;
import by.bsuir.lab3.server.model.command.impl.record.DeleteRecordCommandImpl;
import by.bsuir.lab3.server.model.command.impl.record.GetAllRecordsCommandImpl;
import by.bsuir.lab3.server.model.command.impl.record.GetRecordsByGroupCommandImpl;
import by.bsuir.lab3.server.model.command.impl.record.UpdateRecordCommandImpl;
import by.bsuir.lab3.server.model.command.impl.record.UpdateRecordStorageCommandImpl;

public class CommandMapFactory {
    private static final Map<UserRole, Map<RequestCode, Command>> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put(null, getUnauthorizedCommands());
        commandMap.put(UserRole.ADMIN, getAdminCommands());
        commandMap.put(UserRole.USER, getUserCommands());
    }

    private static Map<RequestCode, Command> getUnauthorizedCommands() {
        HashMap<RequestCode, Command> result = new HashMap<>();

        result.put(RequestCode.ADD_ACCOUNT, new AddAccountCommandImpl());
        result.put(RequestCode.DOES_ACCOUNT_EXIST, new DoesAccountExistCommandImpl());
        result.put(RequestCode.LOGIN, new LoginCommandImpl());
        return result;
    }

    private static Map<RequestCode, Command> getUserCommands() {
        HashMap<RequestCode, Command> result = new HashMap<>();

        result.put(RequestCode.GET_ALL_RECORDS, new GetAllRecordsCommandImpl());
        result.put(RequestCode.GET_RECORDS_BY_GROUP, new GetRecordsByGroupCommandImpl());
        return result;
    }

    private static Map<RequestCode, Command> getAdminCommands() {
        HashMap<RequestCode, Command> result = new HashMap<>();

        result.put(RequestCode.GET_ALL_RECORDS, new GetAllRecordsCommandImpl());
        result.put(RequestCode.GET_RECORDS_BY_GROUP, new GetRecordsByGroupCommandImpl());
        result.put(RequestCode.ADD_ACCOUNT, new AddAccountCommandImpl());
        result.put(RequestCode.ADD_RECORD, new AddRecordCommandImpl());
        result.put(RequestCode.DELETE_RECORD, new DeleteRecordCommandImpl());
        result.put(RequestCode.UPDATE_RECORD, new UpdateRecordCommandImpl());
        result.put(RequestCode.UPDATE_ACCOUNT, new UpdateAccountCommandImpl());
        result.put(RequestCode.UPDATE_ACCOUNT_STORAGE, new UpdateAccountStorageCommandImpl());
        result.put(RequestCode.UPDATE_RECORD_STORAGE, new UpdateRecordStorageCommandImpl());
        return result;
    }

    public static Map<RequestCode, Command> getCommands(UserRole userRole) {
        return new HashMap<>(commandMap.get(userRole));
    }

    private CommandMapFactory() {
    }
}
