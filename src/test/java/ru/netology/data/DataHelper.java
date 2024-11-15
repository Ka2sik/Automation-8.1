package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String generateLogin() {
        return faker.name().username();
    }

    public static String generatePassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateUser() {
        return new AuthInfo(generateLogin(), generatePassword());
    }

    public static String generateVerificationCode() {
        return Faker.instance().numerify("######");
    }
}
