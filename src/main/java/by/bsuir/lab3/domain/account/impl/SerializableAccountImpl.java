package by.bsuir.lab3.domain.account.impl;

import java.io.Serializable;
import java.util.Objects;

import by.bsuir.lab3.domain.account.Account;
import by.bsuir.lab3.domain.account.UserRole;

public class SerializableAccountImpl implements Account, Serializable {
    private String username;
    private String passwordHash;
    private UserRole role;

    public SerializableAccountImpl(String username, String passwordHash, UserRole role) {
        if ((username == null) || (passwordHash == null) || (role == null)) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }

        this.passwordHash = passwordHash.trim();
        this.role = role;
        this.username = username.trim();

        if (this.username.isEmpty() || (this.passwordHash.isEmpty())) {
            throw new IllegalArgumentException("Arguments shouldn't be empty");
        }
    }

    public SerializableAccountImpl(Account account) {
        this(account.getUsername(), account.getPasswordHash(), account.getRole());
    }

    public SerializableAccountImpl() {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        SerializableAccountImpl toCompare;

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        toCompare = (SerializableAccountImpl) o;
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
