package io.github.vikindor.android.ui.screens.auth;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class GoogleScreen {

    private final SelenideElement gmsContainer = $(id("com.google.android.gms:id/container"));

    public GoogleScreen shouldHaveGmsContainer() {
        gmsContainer.shouldBe(Condition.visible);
        return this;
    }
}
