package io.github.vikindor.web.helpers;

import io.github.vikindor.web.ui.pages.LoginPage;

public class AuthHelper {

    public static void login(String email, String password) {
        new LoginPage()
                .setEmail(email)
                .setPassword(password)
                .clickLogInButton();
    }
}
