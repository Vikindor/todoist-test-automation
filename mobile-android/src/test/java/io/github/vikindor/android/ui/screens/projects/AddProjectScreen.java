package io.github.vikindor.android.ui.screens.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;

public class AddProjectScreen {

    private static final SelenideElement
            projectNameInput = $(id("com.todoist:id/name")),
            doneButton = $(id("com.todoist:id/menu_form_submit"));

    public AddProjectScreen setProjectName(String name) {
        projectNameInput.sendKeys(name);
        return this;
    }

    public AddProjectScreen tapDone() {
        doneButton.click();
        return this;
    }
}
