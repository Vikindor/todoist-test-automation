package io.github.vikindor.web.ui.actions;

import static com.codeborne.selenide.Selenide.sleep;
import static io.github.vikindor.web.ui.pages.Pages.*;

public class ProjectActions {

    public static void projectCleanup(String projectName) {
        project()
                .openProjectOptionsMenu()
                .deleteProject();

        sleep(150);

        deleteProjectModal().clickDelete();

        projects()
                .openPage()
                .shouldNotHaveProject(projectName);
    }
}
