package io.github.vikindor.android.tests;

import io.github.vikindor.android.helpers.A;
import io.github.vikindor.android.ui.components.AlertDialog;
import io.github.vikindor.android.ui.components.NavigationBar;
import io.github.vikindor.android.ui.screens.main.BrowseScreen;
import io.github.vikindor.android.ui.screens.projects.AddProjectScreen;
import io.github.vikindor.android.ui.screens.projects.EditProjectScreen;
import io.github.vikindor.android.ui.screens.projects.ProjectScreen;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Epic("Android")
@Feature("Projects")
@Tag("mobile") @Tag("android") @Tag("projects") @Tag("regression")
@DisplayName("Projects")
public class ProjectsTests extends TestBase {

    NavigationBar navigationBar = new NavigationBar();
    BrowseScreen browseScreen =  new BrowseScreen();
    AddProjectScreen addProjectScreen =  new AddProjectScreen();
    ProjectScreen projectScreen =  new ProjectScreen();
    EditProjectScreen editProjectScreen =  new EditProjectScreen();
    AlertDialog alertDialog = new AlertDialog();

    @Test
    @Tag("smoke")
    @DisplayName("Projects CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteProject() {

        String initialName = "Test Project " + System.currentTimeMillis();
        String newName = initialName + " updated";

        navigationBar.tapBrowse();

        step("Create project", () -> {
            browseScreen
                    .tapAdd()
                    .tapAddProject();
            addProjectScreen
                    .setProjectName(initialName)
                    .tapDone();
        });

        step("Read project and verify actual name", () -> {
            projectScreen.verifyProjectName(initialName);
        });

        step("Update project and verify updated name", () -> {
            projectScreen
                    .tapMoreOptions()
                    .tapEdit();
            editProjectScreen
                    .setName(newName)
                    .tapDone();
            projectScreen.verifyProjectName(newName);
            A.back();
            browseScreen.shouldHaveProject(newName);
        });

        step("Delete task and verify it is deleted", () -> {
            browseScreen.openProject(newName);
            projectScreen
                    .tapMoreOptions()
                    .tapDelete();
            alertDialog.tapDelete();
            navigationBar.tapBrowse();
            browseScreen.shouldNotHaveProject(newName);
        });
    }
}
