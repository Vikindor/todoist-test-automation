package io.github.vikindor.android.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class QuickAddItemContainer {

    private final SelenideElement
            messageInput = $(id("android:id/message")),
            addButton = $(accessibilityId("Add"));

    public QuickAddItemContainer setMessage(String message) {
        messageInput.sendKeys(message);
        return this;
    }

    public QuickAddItemContainer tapAdd() {
        addButton.click();
        return this;
    }
}
