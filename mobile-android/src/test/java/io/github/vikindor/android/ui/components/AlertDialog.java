package io.github.vikindor.android.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class AlertDialog {

    private static final SelenideElement
            cancelButton = $(id("android:id/button2")),
            deleteButton = $(id("android:id/button1"));

    public AlertDialog tapCancel() {
        cancelButton.click();
        return this;
    }

    public AlertDialog tapDelete() {
        deleteButton.click();
        return this;
    }
}
