package io.github.vikindor.web.tests;

import io.github.vikindor.web.extensions.WithLogin;
import io.github.vikindor.web.testdata.GeneratedData;
import io.github.vikindor.web.ui.actions.ProjectActions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static io.github.vikindor.web.ui.pages.Pages.*;

@Epic("Web")
@Feature("Projects")
@Tag("web") @Tag("projects") @Tag("regression")
@DisplayName("Projects")
@WithLogin
public class ProjectsTests extends TestBase {

    @Test
    @Tag("smoke")
    @DisplayName("Projects CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteProject() {
        String initialName = GeneratedData.nameOfLength(10);
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

        step("Delete project and verify it is deleted", () -> {
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

    @ParameterizedTest
    @Tag("regression")
    @MethodSource("io.github.vikindor.web.testdata.ProjectNameTestData#validNames")
    @DisplayName("Projects with different valid names can be created")
    void shouldCreateProjectsWithDifferentValidNames(String projectName) {
        step("Create project", () -> {
            projects()
                    .openPage()
                    .clickAddButton()
                    .clickAddProject();
            projectModal()
                    .setName(projectName)
                    .clickSubmitButton();
        });

        step("Verify project name", () -> {
            project().shouldHaveProjectName(projectName);
        });

        ProjectActions.projectCleanup(projectName);
    }

    @Test
    @Tag("regression")
    @DisplayName("Submit button is disabled when project name is blank")
    void shouldNotEnableSubmitButtonWhenNameIsBlank() {
        String projectName = " ";

        step("Open project creation and verify submit button is disabled by default", () -> {
            projects()
                    .openPage()
                    .clickAddButton()
                    .clickAddProject();
            projectModal().shouldHaveDisabledSubmitButton();
        });

        step("Enter blank name and verify submit button is disabled", () -> {
            projectModal()
                    .setName(projectName)
                    .shouldHaveDisabledSubmitButton();
        });
    }
}
