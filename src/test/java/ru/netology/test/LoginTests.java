package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanAuthCodes;
import static ru.netology.data.SQLHelper.cleanTestData;

public class LoginTests {
    LoginPage loginPage;

    @AfterEach
    void cleanAuthCode() {
        cleanAuthCodes();
    }

    @AfterAll
    static void cleanAllTestData() {
        cleanTestData();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    void shouldSuccessfullyLogin() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerification(verificationCode);
    }

    @Test
    void shouldShowErrorIfLoginWithRandomUser() {
        var authInfo = DataHelper.generateUser();
        loginPage.login(authInfo);
        loginPage.showErrorNotification("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void shouldShowErrorIfVerificationCodeIsRandom() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.generateVerificationCode();
        verificationPage.verify(verificationCode);
        verificationPage.showErrorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }

}
