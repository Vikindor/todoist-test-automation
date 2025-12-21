package io.github.vikindor.android.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class ItemDetailsContainer {

    private static final SelenideElement
            taskTitle = $(id("com.todoist:id/item_content")),
            saveButton = $(id("com.todoist:id/item_submit"));

    public ItemDetailsContainer setTitle(String title) {
        taskTitle.sendKeys(title);
        return this;
    }

    public ItemDetailsContainer tapSave() {
        saveButton.click();
        return this;
    }
}
