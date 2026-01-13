package io.github.vikindor.android.ui.actions;

import static io.github.vikindor.android.ui.screens.Screens.*;

public class ProjectActions {

    public static void projectCleanup(String projectName) {
        projectScreen()
                .openProjectOptionsMenu()
                .tapDelete();
        alertDialog().tapDelete();
        navigationBar().tapBrowse();
        browseScreen().shouldNotHaveProject(projectName);
    }
}
