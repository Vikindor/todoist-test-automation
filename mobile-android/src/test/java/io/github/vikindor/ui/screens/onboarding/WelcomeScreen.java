package io.github.vikindor.ui.screens.onboarding;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class WelcomeScreen {

    private static final SelenideElement
            continueWithEmailButton =
            $(androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(0)")),
            loginWithEmailButton =
            $(androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(4)"));

    public WelcomeScreen tapContinueWithEmailButton() {
        continueWithEmailButton.click();
        return this;
    }

    public WelcomeScreen tapLoginWithEmailButton() {
        loginWithEmailButton.click();
        return this;
    }
}
