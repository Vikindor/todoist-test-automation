package io.github.vikindor.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class CreateItemContainer {

    private static final SelenideElement
            overflowButton = $(id("com.todoist:id/item_overflow")),
            deleteTaskAction = $(id("com.todoist:id/delete")),
            taskTitle = $(id("com.todoist:id/item_content"));

    public CreateItemContainer tapOverflowButton() {
        overflowButton.click();
        return this;
    }

    public CreateItemContainer tapDeleteTask() {
        deleteTaskAction.click();
        return this;
    }

    public CreateItemContainer tapTaskTitle() {
        taskTitle.click();
        return this;
    }
}
