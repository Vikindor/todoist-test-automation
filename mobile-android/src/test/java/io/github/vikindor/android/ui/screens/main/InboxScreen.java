package io.github.vikindor.android.ui.screens.main;

public class InboxScreen {

    private final TasksList tasksList = new TasksList();

    public InboxScreen shouldContainTask(String taskName) {
        tasksList.shouldContainTask(taskName);
        return this;
    }

    public InboxScreen shouldNotContainTask(String taskName) {
        tasksList.shouldNotContainTask(taskName);
        return this;
    }

    public InboxScreen openTask(String taskName) {
        tasksList.openTask(taskName);
        return this;
    }

    public InboxScreen completeTask(String taskName) {
        tasksList.checkmarkTask(taskName);
        return this;
    }
}
