package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement header = $("[data-test-id=dashboard]");

    public DashboardPage() {
        header.shouldHave(exactText("Личный кабинет")).shouldBe(visible);
    }
}
