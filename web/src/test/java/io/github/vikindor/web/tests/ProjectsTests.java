package io.github.vikindor.web.tests;

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
public class ProjectsTests extends TestBase {

    ProjectsPage projectsPage() { return new ProjectsPage(); }
    ProjectPage projectPage() { return new ProjectPage(); }
    ProjectModal projectModal() { return new ProjectModal(); }
    DeleteModal deleteProjectModal() { return new DeleteModal(); }

    @Test
    @Tag("smoke")
    @DisplayName("Projects CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteProject() {

        String initialName = "Test Project " + System.currentTimeMillis();
        String newName = initialName + " updated";

        step("Create project", () -> {
            projectsPage()
                    .openPage()
                    .clickAddButton()
                    .clickAddProject();
            projectModal()
                    .setName(initialName)
                    .clickSubmitButton();
        });

        step("Read project and verify actual name", () -> {
            projectPage().shouldHaveProjectName(initialName);
        });

        step("Update project and verify updated name", () -> {
            projectPage()
                    .openProjectOptionsMenu()
                    .editProject();
            projectModal()
                    .setName(newName)
                    .clickSubmitButton();
            projectPage().shouldHaveProjectName(newName);
        });

        step("Delete task and verify it is deleted", () -> {
            projectPage()
                    .openProjectOptionsMenu()
                    .deleteProject();

            step("Wait for backend to be ready for delete confirmation", () -> {
                sleep(150);
            });

            deleteProjectModal().clickDelete();
            projectsPage()
                    .openPage()
                    .shouldNotHaveProject(newName);
        });
    }
}
