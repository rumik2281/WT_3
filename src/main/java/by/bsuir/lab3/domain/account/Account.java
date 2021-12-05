package by.bsuir.lab3.domain.account;

public interface Account {
    String getUsername();

    String getPasswordHash();

    UserRole getRole();
}
