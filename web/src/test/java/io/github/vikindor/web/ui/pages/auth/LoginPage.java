package io.github.vikindor.web.ui.pages.auth;

import com.codeborne.selenide.SelenideElement;
import io.github.vikindor.web.ui.pages.main.InboxPage;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement
            emailInput = $("input[type='email']"),
            passwordInput = $("input[type='password']"),
            logInButton = $("button[type='submit']"),
            emailValidationError =
                    $("form").$$("div").findBy(exactText("Please enter a valid email address.")),
            passwordValidationError =
                    $("form").$$("p").findBy(exactText("Passwords must be at least 8 characters long.")),
            wrongEmailOrPasswordError = $("form").$$("div").findBy(exactText("Wrong email or password.")),
            forgotPasswordLink = $$("a").findBy(exactText("Forgot your password?")),
            signUpLink = $$("a").findBy(exactText("Sign up")),
            continueWithGoogleButton = $$("a").findBy(exactText("Continue with Google")),
            continueWithFacebookButton = $$("a").findBy(exactText("Continue with Facebook")),
            continueWithAppleButton = $$("a").findBy(exactText("Continue with Apple"));

    public LoginPage openPage() {
        open("/auth/login");
        return this;
    }

    public LoginPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    public InboxPage clickLogInButton() {
        logInButton.click();
        waitForAuthStabilization();
        return new InboxPage();
    }

    public ForgotPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new ForgotPasswordPage();
    }

    public SignUpPage clickSignUpLink() {
        signUpLink.click();
        return new SignUpPage();
    }

    public GooglePage clickGoogleButton() {
        continueWithGoogleButton.click();
        return new GooglePage();
    }

    public FacebookPage clickFacebookButton() {
        continueWithFacebookButton.click();
        return new FacebookPage();
    }

    public ApplePage clickAppleButton() {
        continueWithAppleButton.click();
        return new ApplePage();
    }

    public LoginPage shouldShowEmailValidationError() {
        emailValidationError.shouldBe(visible);
        return this;
    }

    public LoginPage shouldShowPasswordValidationError() {
        passwordValidationError.shouldBe(visible);
        return this;
    }

    public LoginPage shouldShowWrongEmailOrPasswordError() {
        wrongEmailOrPasswordError.shouldBe(visible);
        return this;
    }

    private static void waitForAuthStabilization() {
        // Todoist uses a SPA auth flow where the authentication context is finalized
        // asynchronously after the login action.
        // There is no reliable DOM, cookie, or storage signal (tried everything) that indicates
        // when the auth state becomes stable and backend routing stops returning redirects.
        // A short fixed delay here acts as a synchronization barrier to let the auth
        // handshake fully complete before performing direct navigation.
        sleep(900);
    }
}
