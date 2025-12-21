package io.github.vikindor.helpers;

import io.github.vikindor.ui.screens.auth.LoginScreen;
import io.github.vikindor.ui.screens.onboarding.WelcomeScreen;

public class AuthHelper {

    public static void login(String email, String password) {
        new WelcomeScreen()
                .tapContinueWithEmailButton()
                .tapLoginWithEmailButton();

        new LoginScreen()
                .setEmail(email)
                .setPassword(password)
                .tapLoginButton();
    }
}
