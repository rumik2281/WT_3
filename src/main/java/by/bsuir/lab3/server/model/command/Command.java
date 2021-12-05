package by.bsuir.lab3.server.model.command;

public interface Command {
    String[] execute(String[] request) throws CommandException;
}
