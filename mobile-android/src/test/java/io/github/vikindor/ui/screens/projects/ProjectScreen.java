package io.github.vikindor.ui.screens.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.*;

public class ProjectScreen {

    private final static SelenideElement
            moreOptionsButton = $(androidUIAutomator("new UiSelector().description(\"More options\")")),
            editAction = $(androidUIAutomator("new UiSelector().text(\"Edit\")")),
            deleteAction = $(androidUIAutomator("new UiSelector().text(\"Delete\")"));

    private SelenideElement projectByTitle(String title) {
        return $(androidUIAutomator("new UiSelector().text(\"" + title + "\")"));
    }

    public ProjectScreen verifyProjectName(String title) {
        projectByTitle(title).shouldBe(visible);
        return this;
    }

    public ProjectScreen tapMoreOptions() {
        moreOptionsButton.click();
        return this;
    }

    public ProjectScreen tapEdit() {
        editAction.click();
        return this;
    }

    public ProjectScreen tapDelete() {
        deleteAction.click();
        return this;
    }
}
