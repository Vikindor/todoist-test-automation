package io.github.vikindor.android.tests;

import com.codeborne.selenide.Selenide;
import io.github.vikindor.android.configs.ProjectConfig;
import io.github.vikindor.android.helpers.Android;
import io.github.vikindor.android.ui.screens.auth.GoogleScreen;
import io.github.vikindor.android.ui.screens.auth.WelcomeScreen;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Epic("Android")
@Feature("Login")
@Tag("mobile") @Tag("android") @Tag("login") @Tag("smoke")
@DisplayName("Login")
public class LoginTests extends TestBase {

    private static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    WelcomeScreen welcomeScreen() {
        return new WelcomeScreen();
    }

    GoogleScreen googleScreen() {
        return new GoogleScreen();
    }

    @Test
    @Tag("smoke")
    @DisplayName("Open Google sign in")
    void shouldOpenGoogleSignIn() {
        step("Open Google sign in", welcomeScreen()::tapContinueWithGoogleButton);

        step("Verify Google gms opened", googleScreen()::shouldHaveGmsContainer);
    }

    @Test
    @Tag("smoke")
    @DisplayName("Open Facebook sign in")
    void shouldOpenFacebookSignIn() {
        String appPackage = config.appPackage();

        step("Open Facebook sign in", welcomeScreen()::tapContinueWithFacebookButton);

        step("Verify external app opening attempt", () -> {
            Selenide.Wait().until(driver ->
                    !Android.getCurrentPackage().equals(appPackage)
            );
        });
    }

}
