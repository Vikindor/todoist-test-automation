package io.github.vikindor.web.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

public class ProjectModal {

    private final SelenideElement
            modal = $("[data-testid='modal-overlay']"),
            nameInput = modal.$("input[name='name']"),
            submitButton = modal.$("button[type='submit']");

    public ProjectModal setName(String projectName) {
        nameInput.setValue(projectName);
        return this;
    }

    public ProjectModal clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public ProjectModal shouldHaveDisabledSubmitButton() {
        submitButton.shouldHave(attribute("aria-disabled", "true"));
        return this;
    }
}
