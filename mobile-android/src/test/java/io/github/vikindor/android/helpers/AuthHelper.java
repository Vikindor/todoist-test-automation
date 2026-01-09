package io.github.vikindor.android.helpers;

import io.github.vikindor.android.ui.screens.auth.LoginScreen;
import io.github.vikindor.android.ui.screens.auth.WelcomeScreen;

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
