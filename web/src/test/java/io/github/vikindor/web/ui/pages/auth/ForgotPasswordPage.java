package io.github.vikindor.web.ui.pages.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class ForgotPasswordPage {

    private final SelenideElement forgotPasswordTitle = $$("h1").findBy(exactText("Forgot your password?"));

    public ForgotPasswordPage shouldHaveTitle() {
        forgotPasswordTitle.shouldBe(visible);
        return this;
    }
}
