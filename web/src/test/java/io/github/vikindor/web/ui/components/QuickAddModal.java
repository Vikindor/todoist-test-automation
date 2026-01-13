package io.github.vikindor.web.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class QuickAddModal {

    private final SelenideElement
            taskNameInput = $("[aria-label='Task name']"),
            submitButton = $("[data-testid='task-editor-submit-button']"),
            taskNameLimitError = $("#a11y_task_name_over_limit");

    public QuickAddModal setTaskName(String taskName) {
        taskNameInput.setValue(taskName);
        return this;
    }

    public QuickAddModal clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public QuickAddModal shouldShowCharacterLimitError() {
        taskNameLimitError.shouldBe(visible);
        return this;
    }

    public QuickAddModal shouldHaveDisabledSubmitButton() {
        submitButton.shouldHave(attribute("aria-disabled", "true"));
        return this;
    }
}
