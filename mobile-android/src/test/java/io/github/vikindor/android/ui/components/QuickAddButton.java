package io.github.vikindor.android.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class QuickAddButton {

    private final SelenideElement quickAddButton = $(accessibilityId("Quick add"));

    public QuickAddButton tap() {
        quickAddButton.click();
        return this;
    }
}
