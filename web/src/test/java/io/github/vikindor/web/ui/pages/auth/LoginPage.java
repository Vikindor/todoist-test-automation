package io.github.vikindor.web.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement
            emailInput = $("input[type='email']"),
            passwordInput = $("input[type='password']"),
            logInButton = $("button[type='submit']");

    public void openPage() {
        open("/auth/login");
    }

    public LoginPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public LoginPage clickLogInButton() {
        logInButton.click();
        return this;
    }
}
