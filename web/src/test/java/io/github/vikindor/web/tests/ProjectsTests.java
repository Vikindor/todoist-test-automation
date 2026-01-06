package io.github.vikindor.web.tests;

import io.github.vikindor.web.extensions.WithLogin;
import io.github.vikindor.web.ui.components.ProjectModal;
import io.github.vikindor.web.ui.components.DeleteModal;
import io.github.vikindor.web.ui.pages.projects.ProjectPage;
import io.github.vikindor.web.ui.pages.projects.ProjectsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;

@Epic("Web")
@Feature("Projects")
@Tag("web")  @Tag("projects") @Tag("regression")
@DisplayName("Projects")
@WithLogin
public class ProjectsTests extends TestBase {

    ProjectsPage projects() { return new ProjectsPage(); }
    ProjectPage project() { return new ProjectPage(); }
    ProjectModal projectModal() { return new ProjectModal(); }
    DeleteModal deleteProjectModal() { return new DeleteModal(); }

    @Test
    @Tag("smoke")
    @DisplayName("Projects CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteProject() {

        String initialName = "Test Project " + System.currentTimeMillis();
        String newName = initialName + " updated";

        step("Create project", () -> {
            projects()
                    .openPage()
                    .clickAddButton()
                    .clickAddProject();
            projectModal()
                    .setName(initialName)
                    .clickSubmitButton();
        });

        step("Read project and verify actual name", () -> {
            project().shouldHaveProjectName(initialName);
        });

        step("Update project and verify updated name", () -> {
            project()
                    .openProjectOptionsMenu()
                    .editProject();
            projectModal()
                    .setName(newName)
                    .clickSubmitButton();
            project().shouldHaveProjectName(newName);
        });

        step("Delete task and verify it is deleted", () -> {
            project()
                    .openProjectOptionsMenu()
                    .deleteProject();

            step("Wait for backend to be ready for delete confirmation", () -> {
                sleep(150);
            });

            deleteProjectModal().clickDelete();
            projects()
                    .openPage()
                    .shouldNotHaveProject(newName);
        });
    }
}
