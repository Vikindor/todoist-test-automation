package io.github.vikindor.web.helpers;

import io.github.vikindor.web.ui.pages.auth.LoginPage;

public class AuthHelper {

    public static void login(String email, String password) {

        LoginPage loginPage = new LoginPage();

        loginPage.openPage();
        loginPage
                .setEmail(email)
                .setPassword(password)
                .clickLogInButton();
    }
}
