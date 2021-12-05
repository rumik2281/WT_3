package by.bsuir.lab3.domain.digest;

public interface PasswordDigestGenerator {
    String generate(String password) throws PasswordDigestException;
}
