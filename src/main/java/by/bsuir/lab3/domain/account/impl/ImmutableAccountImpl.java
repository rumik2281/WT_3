package by.bsuir.lab3.domain.account.impl;

import java.util.Objects;

import by.bsuir.lab3.domain.account.Account;
import by.bsuir.lab3.domain.account.UserRole;

public class ImmutableAccountImpl implements Account {
    private final String username;
    private final String passwordHash;
    private final UserRole role;

    public ImmutableAccountImpl(String username, String passwordHash, UserRole role) {
        if ((username == null) || (passwordHash == null) || (role == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        this.passwordHash = passwordHash.trim();
        this.role = role;
        this.username = username.trim();

        if (this.username.isEmpty() || this.passwordHash.isEmpty()) {
            throw new IllegalArgumentException("Arguments cannot be empty");
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        ImmutableAccountImpl toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (ImmutableAccountImpl) o;
        return Objects.equals(username, toCompare.username) && Objects.equals(passwordHash, toCompare.passwordHash)
                && role == toCompare.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordHash, role);
    }

    @Override
    public String toString() {
        return getClass().getName() + "@username: " + username + ", passwordHash: " + passwordHash + ", role: " + role;
    }

}
