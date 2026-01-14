package io.github.vikindor.android.tests;

import io.github.vikindor.android.extensions.WithLogin;
import io.github.vikindor.android.helpers.Android;
import io.github.vikindor.android.testdata.GeneratedData;
import io.github.vikindor.android.ui.actions.ProjectActions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.qameta.allure.Allure.step;
import static io.github.vikindor.android.ui.screens.Screens.*;

@Epic("Android")
@Feature("Projects")
@Tag("mobile") @Tag("android") @Tag("projects") @Tag("regression")
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
            navigationBar().tapBrowse();
            browseScreen()
                    .tapAdd()
                    .tapAddProject();
            addProjectScreen()
                    .setProjectName(initialName)
                    .tapDone();
        });

        step("Read project and verify actual name", () -> {
            projectScreen().shouldHaveProjectName(initialName);
        });

        step("Update project and verify updated name", () -> {
            projectScreen()
                    .openProjectOptionsMenu()
                    .tapEdit();
            editProjectScreen()
                    .setName(newName)
                    .tapDone();
            projectScreen().shouldHaveProjectName(newName);
            Android.back();
            browseScreen().shouldHaveProject(newName);
        });

        step("Delete project and verify it is deleted", () -> {
            browseScreen().openProject(newName);
            projectScreen()
                    .openProjectOptionsMenu()
                    .tapDelete();
            alertDialog().tapDelete();
            navigationBar().tapBrowse();
            browseScreen().shouldNotHaveProject(newName);
        });
    }

    @ParameterizedTest
    @Tag("regression")
    @MethodSource("io.github.vikindor.android.testdata.ProjectNameTestData#validNames")
    @DisplayName("Projects with different valid names can be created")
    void shouldCreateProjectsWithDifferentValidNames(String projectName) {
        step("Create project", () -> {
            navigationBar().tapBrowse();
            browseScreen()
                    .tapAdd()
                    .tapAddProject();
            addProjectScreen()
                    .setProjectName(projectName)
                    .tapDone();
        });

        step("Read project and verify actual name", () -> {
            projectScreen().shouldHaveProjectName(projectName);
        });

        ProjectActions.projectCleanup();
    }

    @Test
    @Tag("regression")
    @DisplayName("Error is shown when submitting blank project name")
    void shouldShowErrorWhenSubmittingBlankProjectName() {
        String projectName = " ";

        step("Open project creation and enter blank project name", () -> {
            navigationBar().tapBrowse();
            browseScreen()
                    .tapAdd()
                    .tapAddProject();
            addProjectScreen().setProjectName(projectName);
        });

        step("Submit and verify error is shown", () -> {
            addProjectScreen()
                    .tapDone()
                    .shouldShowNameRequiredError();
        });
    }
}
