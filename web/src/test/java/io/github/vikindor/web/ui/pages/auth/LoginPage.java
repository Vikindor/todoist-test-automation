package io.github.vikindor.web.ui.pages.auth;

import com.codeborne.selenide.SelenideElement;
import io.github.vikindor.web.ui.pages.main.InboxPage;

import static com.codeborne.selenide.Selenide.*;

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

    public InboxPage clickLogInButton() {
        logInButton.click();
        waitForAuthStabilization();
        return new InboxPage();
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
