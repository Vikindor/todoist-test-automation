package io.github.vikindor.web.tests;

import io.github.vikindor.web.configs.ProjectConfig;
import io.github.vikindor.web.testdata.GeneratedData;
import io.github.vikindor.web.ui.pages.auth.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.github.vikindor.web.ui.pages.Pages.*;

@Epic("Web")
@Feature("Login")
@Tag("web") @Tag("login") @Tag("regression")
@DisplayName("Login")
public class LoginTests extends TestBase {

    private static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @Test
    @Tag("smoke")
    @DisplayName("Email validation error is shown for invalid email")
    void shouldShowEmailValidationError() {
        String invalidEmail = GeneratedData.invalidEmail();
        String invalidPassword = GeneratedData.invalidPassword();

        step("Submit invalid email and password", () -> {
            loginPage()
                    .openPage()
                    .setEmail(invalidEmail)
                    .setPassword(invalidPassword)
                    .clickLogInButton();
        });

        step("Verify email validation error", loginPage()::shouldShowEmailValidationError);
    }

    @Test
    @Tag("smoke")
    @DisplayName("Password validation error is shown for empty password")
    void shouldShowPasswordValidationError() {
        String invalidEmail = GeneratedData.invalidEmail();

        step("Submit email with empty password", () -> {
            loginPage()
                    .openPage()
                    .setEmail(invalidEmail)
                    .clickLogInButton();
        });

        step("Verify password validation error", loginPage()::shouldShowPasswordValidationError);
    }

    @Test
    @Tag("regression")
    @DisplayName("Error is shown for wrong password")
    void shouldShowWrongEmailOrPasswordError() {
        String invalidPassword = GeneratedData.invalidPassword();

        step("Submit valid email with invalid password", () -> {
            loginPage()
                    .openPage()
                    .setEmail(config.todoistEmail())
                    .setPassword(invalidPassword)
                    .clickLogInButton();
        });

        step("Verify wrong email/password error", loginPage()::shouldShowWrongEmailOrPasswordError);
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Forgot password page from Login page")
    void shouldNavigateToForgotPasswordPage() {
        step("Navigate to Forgot password page", () -> {
            loginPage()
                    .openPage()
                    .clickForgotPasswordLink();
        });

        step("Verify page title", forgotPasswordPage()::shouldHaveTitle);
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Sign up page from Login page")
    void shouldNavigateToSignUpPage() {
        step("Navigate to Sign up page", () -> {
            loginPage()
                    .openPage()
                    .clickSignUpLink();
        });

        step("Verify page title", signUpPage()::shouldHaveTitle);
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Google sign in page from Login page")
    void shouldNavigateToGoogleSignInPage() {
        step("Navigate to Google sign in page", () -> {
            loginPage()
                    .openPage()
                    .clickGoogleButton();
        });

        step("Verify Google sign in frame", googlePage()::shouldHaveIFrame);
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Facebook sign in page from Login page")
    void shouldNavigateToFacebookSignInPage() {
        step("Navigate to Facebook sign in page", () -> {
            loginPage()
                    .openPage()
                    .clickFacebookButton();
        });

        step("Verify Facebook title", facebookPage()::shouldHaveTitle);
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Apple sign in page from Login page")
    void shouldNavigateToAppleSignInPage() {
        step("Navigate to Apple sign in page", () -> {
            loginPage()
                    .openPage()
                    .clickAppleButton();
        });

        step("Verify Apple page opened", applePage()::shouldBeOpened);
    }
}
