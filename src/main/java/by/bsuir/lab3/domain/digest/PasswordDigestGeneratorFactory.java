package by.bsuir.lab3.domain.digest;

import by.bsuir.lab3.domain.digest.impl.Md5DigestGeneratorImpl;

public class PasswordDigestGeneratorFactory {
    private final static PasswordDigestGenerator passwordDigestGenerator = new Md5DigestGeneratorImpl();

    public static PasswordDigestGenerator getPasswordDigestGenerator() {
        return passwordDigestGenerator;
    }

    private PasswordDigestGeneratorFactory() {
    }
}
