package io.github.vikindor.android.ui.actions;

import static io.github.vikindor.android.ui.screens.Screens.projectScreen;
import static io.github.vikindor.android.ui.screens.Screens.alertDialog;

public class ProjectActions {

    public static void projectCleanup() {
        projectScreen()
                .openProjectOptionsMenu()
                .tapDelete();

        alertDialog().tapDelete();
    }
}
