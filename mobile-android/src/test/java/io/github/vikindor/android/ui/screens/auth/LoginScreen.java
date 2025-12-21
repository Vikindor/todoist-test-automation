package io.github.vikindor.android.ui.screens.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class LoginScreen {

    private static final SelenideElement
            emailInput = $(androidUIAutomator("new UiSelector().resourceId(\"email\")")),
            passwordInput = $(androidUIAutomator("new UiSelector().resourceId(\"password\")")),
            loginButton = $(androidUIAutomator("new UiSelector().resourceId(\"auth_button_tag\")"));

    public LoginScreen setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginScreen setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginScreen tapLoginButton() {
        loginButton.click();
        return this;
    }
}
