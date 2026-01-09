package io.github.vikindor.android.tests;

import com.codeborne.selenide.Selenide;
import io.github.vikindor.android.configs.ProjectConfig;
import io.github.vikindor.android.helpers.Android;
import io.github.vikindor.android.ui.screens.auth.GoogleScreen;
import io.github.vikindor.android.ui.screens.auth.WelcomeScreen;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class LoginTests extends TestBase {

    private static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    WelcomeScreen welcomeScreen() {
        return new WelcomeScreen();
    }

    GoogleScreen googleScreen() {
        return new GoogleScreen();
    }

    @Test
    void shouldOpenGoogleSignIn() {
        step("Open Google sign in", welcomeScreen()::tapContinueWithGoogleButton);

        step("Verify Google gms opened", googleScreen()::shouldHaveGmsContainer);
    }

    @Test
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
