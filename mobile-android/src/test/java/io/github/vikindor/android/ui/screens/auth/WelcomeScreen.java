package io.github.vikindor.android.ui.screens.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class WelcomeScreen {

    private final SelenideElement
            continueWithEmailButton =
            $(androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")),
            loginWithEmailButton =
            $(androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(4)")),
            continueWithGoogleButton =
            $(androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)")),
            continueWithFacebookButton =
            $(androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(2)"));

    public WelcomeScreen tapContinueWithEmailButton() {
        continueWithEmailButton.click();
        return this;
    }

    public WelcomeScreen tapLoginWithEmailButton() {
        loginWithEmailButton.click();
        return this;
    }

    public void tapContinueWithGoogleButton() {
        continueWithGoogleButton.click();
    }

    public void tapContinueWithFacebookButton() {
        continueWithFacebookButton.click();
    }
}
