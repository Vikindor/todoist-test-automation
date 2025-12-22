package io.github.vikindor.web.ui.pages.main;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class InboxPage {

    private final SelenideElement sidebarContainer = $("[data-testid='app-sidebar-container']");

    private final TasksList tasksList =  new TasksList();
    private final TaskEditMenu taskEditMenu = new TaskEditMenu();

    public InboxPage openPage() {
        open("/app/inbox");
        return this;
    }

    public InboxPage clickAddTaskButton() {
        sidebarContainer.$$("button").findBy(text("Add Task")).click();
        return this;
    }

    public InboxPage shouldContainTask(String taskName) {
        tasksList.shouldContainTask(taskName);
        return this;
    }

    public InboxPage shouldNotContainTask(String taskName) {
        tasksList.shouldNotContainTask(taskName);
        return this;
    }

    public InboxPage completeTask(String taskName) {
        tasksList.clickTaskCheckmark(taskName);
        return this;
    }

    public InboxPage editTask(String taskName) {
        tasksList.clickEdit(taskName);
        return this;
    }

    public InboxPage deleteTask() {
        taskEditMenu.clickDelete();
        return this;
    }

    public InboxPage setTaskName(String taskName) {
        tasksList.setTaskName(taskName);
        return this;
    }

    public InboxPage saveTaskChanges() {
        tasksList.clickSave();
        return this;
    }

    public InboxPage openTaskEditMenu(String taskName) {
        tasksList.openMoreMenu(taskName);
        return this;
    }
}
