package io.github.vikindor.web.ui.pages.main;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TasksList {

    private final ElementsCollection taskItems = $$("[data-testid='task-list-item']");
    private final SelenideElement
            taskNameInput = $("[aria-label='Task name']"),
            saveButton = $("[data-testid='task-editor-submit-button']");

    void shouldContainTask(String taskName) {
        taskItems.findBy(text(taskName)).shouldBe(visible);
    }

    void shouldNotContainTask(String taskName) {
        taskItems.findBy(text(taskName)).shouldNotBe(visible);
    }

    void clickTaskCheckmark(String taskName) { new TaskItem(taskItems.findBy(text(taskName))).clickTaskCheckmark(); }

    void clickEdit(String taskName) {
        new TaskItem(taskItems.findBy(text(taskName))).clickEditAction();
    }

    void setTaskName(String taskName) {
        taskNameInput.setValue(taskName);
    }

    void clickSave() {
        saveButton.click();
    }

    void openMoreMenu(String taskName) { new TaskItem(taskItems.findBy(text(taskName))).openMoreMenu(); }

}
