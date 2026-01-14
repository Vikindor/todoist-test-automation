package io.github.vikindor.android.ui.components;

import com.codeborne.selenide.SelenideElement;
import io.github.vikindor.android.helpers.Android;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class QuickAddItemContainer {

    private final SelenideElement
            messageInput = $(id("android:id/message")),
            addButton = $(accessibilityId("Add"));

    public QuickAddItemContainer setTaskName(String taskName) {
        messageInput.sendKeys(taskName);
        return this;
    }

    public QuickAddItemContainer tapSubmit() {
        addButton.click();
        return this;
    }

    public QuickAddItemContainer shouldShowContentRequiredError() {
        Android.shouldHaveToast("Content is required.");
        return this;
    }
}
