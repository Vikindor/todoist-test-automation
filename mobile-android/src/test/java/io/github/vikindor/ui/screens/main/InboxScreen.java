package io.github.vikindor.ui.screens.main;

import io.github.vikindor.ui.components.TasksList;

public class InboxScreen {

    private final TasksList tasksList = new TasksList();

    public InboxScreen shouldContainTask(String title) {
        tasksList.shouldContainTask(title);
        return this;
    }

    public InboxScreen shouldNotContainTask(String title) {
        tasksList.shouldNotContainTask(title);
        return this;
    }

    public InboxScreen openTask(String title) {
        tasksList.openTask(title);
        return this;
    }

    public InboxScreen completeTask(String title) {
        tasksList.checkmarkTask(title);
        return this;
    }
}
