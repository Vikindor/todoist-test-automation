package io.github.vikindor.web.ui.pages.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class SignUpPage {

    private final SelenideElement signUpTitle = $$("h1").findBy(exactText("Sign up"));

    public SignUpPage shouldHaveTitle() {
        signUpTitle.shouldBe(visible);
        return this;
    }
}