package io.github.vikindor.android.tests;

import io.github.vikindor.android.configs.ProjectConfig;
import io.github.vikindor.android.helpers.Android;
import io.github.vikindor.android.testdata.GeneratedData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.github.vikindor.android.ui.screens.Screens.*;

@Epic("Android")
@Feature("Login")
@Tag("mobile") @Tag("android") @Tag("login") @Tag("smoke")
@DisplayName("Login")
public class LoginTests extends TestBase {

    private static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @Test
    @Tag("smoke")
    @DisplayName("Email validation error is shown for invalid email")
    void shouldShowEmailValidationError() {
        String invalidEmail = GeneratedData.invalidEmail();
        String invalidPassword = GeneratedData.invalidPassword();

        welcomeScreen()
                .tapContinueWithEmailButton()
                .tapLoginWithEmailButton();

        step("Submit invalid email and password", () -> {
            loginScreen()
                    .setEmail(invalidEmail)
                    .setPassword(invalidPassword)
                    .tapLoginButton();
        });

        step("Verify email validation error", loginScreen()::shouldShowEmailValidationError);
    }

    @Test
    @Tag("regression")
    @DisplayName("Wrong credentials toast is shown for wrong password")
    void shouldShowWrongEmailOrPasswordError() {
        String invalidPassword = GeneratedData.invalidPassword();

        welcomeScreen()
                .tapContinueWithEmailButton()
                .tapLoginWithEmailButton();

        step("Submit valid email with invalid password", () -> {
            loginScreen()
                    .setEmail(config.todoistEmail())
                    .setPassword(invalidPassword)
                    .tapLoginButton();
        });

        step("Verify wrong credentials error", loginScreen()::shouldShowCredentialsError);
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Forgot password from Login screen")
    void shouldNavigateToForgotPasswordPage() {
        String packageName = config.appPackage();

        welcomeScreen()
                .tapContinueWithEmailButton()
                .tapLoginWithEmailButton();

        step("Navigate to Forgot your password", loginScreen()::tapForgotYourPasswordButton);

        step("Verify external app opening attempt", () -> {
            Android.waitForExternalAppOpen(packageName);
        });
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Google sign in from Welcome screen")
    void shouldOpenGoogleSignIn() {
        step("Open Google sign in", welcomeScreen()::tapContinueWithGoogleButton);

        step("Verify Google gms opened", googleScreen()::shouldHaveGmsContainer);
    }

    @Test
    @Tag("smoke")
    @DisplayName("User can navigate to Facebook sign in from Welcome screen")
    void shouldOpenFacebookSignIn() {
        String packageName = config.appPackage();

        step("Open Facebook sign in", welcomeScreen()::tapContinueWithFacebookButton);

        step("Verify external app opening attempt", () -> {
            Android.waitForExternalAppOpen(packageName);
        });
    }
}
