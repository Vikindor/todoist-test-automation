package io.github.vikindor.web.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class QuickAddModal {

    private final SelenideElement
            taskNameInput = $("[aria-label='Task name']"),
            submitButton = $("[data-testid='task-editor-submit-button']");

    public QuickAddModal setTaskName(String taskName) {
        taskNameInput.setValue(taskName);
        return this;
    }

    public QuickAddModal clickSubmitButton() {
        submitButton.click();
        return this;
    }

}
