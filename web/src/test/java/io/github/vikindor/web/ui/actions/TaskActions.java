package io.github.vikindor.web.ui.actions;

import static io.github.vikindor.web.ui.pages.Pages.*;

public class TaskActions {

    public static void taskCleanup(String taskName) {
        inbox()
                .openTaskEditMenu(taskName)
                .deleteTask();

        deleteTaskModal().clickDelete();

        inbox().shouldNotContainTask(taskName);
    }
}
