package io.github.vikindor.android.ui.screens.auth;

import com.codeborne.selenide.SelenideElement;
import io.github.vikindor.android.helpers.Android;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class LoginScreen {

    private final SelenideElement
            emailInput = $(androidUIAutomator("new UiSelector().resourceId(\"email\")")),
            passwordInput = $(androidUIAutomator("new UiSelector().resourceId(\"password\")")),
            loginButton = $(androidUIAutomator("new UiSelector().resourceId(\"auth_button_tag\")")),
            forgotYourPasswordButton =
            $(androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)")),
            emailValidationError = $(androidUIAutomator("new UiSelector().text(\"Email is invalid\")"));

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

    public void tapForgotYourPasswordButton() {
        forgotYourPasswordButton.click();
    }

    public LoginScreen shouldShowEmailValidationError() {
        emailValidationError.shouldBe(visible);
        return this;
    }

    public LoginScreen shouldShowCredentialsError() {
        Android.shouldHaveToast("Couldn't log in. Please check your credentials and try again.");
        return this;
    }
}
