package io.github.vikindor.android.ui.actions;

import static io.github.vikindor.android.ui.screens.Screens.*;

public class TaskActions {

    public static void taskCleanup(String taskName) {
        inbox().openTask(taskName);

        createItemContainer()
                .tapOverflowButton()
                .tapDeleteTask();

        alertDialog().tapDelete();

        inbox().shouldNotContainTask(taskName);
    }
}
