package io.github.vikindor.android.ui.screens.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class AddProjectScreen {

    private final SelenideElement
            projectNameInput = $(id("com.todoist:id/name")),
            doneButton = $(id("com.todoist:id/menu_form_submit")),
            projectNameError = $(id("com.todoist:id/textinput_error"));

    public AddProjectScreen setProjectName(String projectName) {
        projectNameInput.sendKeys(projectName);
        return this;
    }

    public AddProjectScreen tapDone() {
        doneButton.click();
        return this;
    }

    public AddProjectScreen shouldShowNameRequiredError() {
        projectNameError.shouldHave(text("Name is required."));
        return this;
    }
}
