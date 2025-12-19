package io.github.vikindor.tests;

import io.github.vikindor.models.ErrorResponse;
import io.github.vikindor.models.ProjectResponse;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.github.vikindor.api.ProjectsApi.*;
import static io.github.vikindor.specs.ApiSpecs.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("API")
@Feature("Projects")
@Tag("api") @Tag("projects") @Tag("regression")
@DisplayName("Projects")
public class ProjectsTests extends TestBase {

    @Test
    @Tag("smoke")
    @DisplayName("Project CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteProject() {

        String initialName = "Test Project " + System.currentTimeMillis();
        String newName = initialName + " updated";

        ProjectResponse createdProject = step("Create project", () ->
                createProject(authSpec(), initialName)
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(ProjectResponse.class)
        );

        step("Read project and verify actual name", () -> {
            ProjectResponse project =
                    getProjectById(authSpec(), createdProject.id)
                            .then()
                            .spec(responseSpec(200))
                            .extract()
                            .as(ProjectResponse.class);

            assertThat(project.name).isEqualTo(initialName);
        });

        step("Update project and verify updated name", () -> {
            ProjectResponse updatedProject =
                    updateProject(authSpec(), createdProject.id, newName)
                            .then()
                            .spec(responseSpec(200))
                            .extract()
                            .as(ProjectResponse.class);

            assertThat(updatedProject.name).isEqualTo(newName);
        });

        step("Delete project", () -> {
            deleteProject(authSpec(), createdProject.id)
                    .then()
                    .spec(responseSpec(204));
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Projects cannot be retrieved without auth token")
    void shouldNotGetProjectsWithoutToken() {
        getProjects(baseSpec())
                .then()
                .spec(responseSpec(401));
    }

    @Test
    @Tag("negative")
    @DisplayName("Projects cannot be retrieved with invalid auth token")
    void shouldNotGetProjectsWithInvalidToken() {
        getProjects(baseSpec().header("Authorization", "Bearer invalid"))
                .then()
                .spec(responseSpec(401));
    }

    @Test
    @Tag("negative")
    @DisplayName("Error when creating project without name")
    void shouldNotCreateProjectWithoutName() {

        ErrorResponse errorResponse = step("Create project with empty name", () ->
                createProject(authSpec(), "")
                        .then()
                        .spec(responseSpec(400))
                        .extract()
                        .as(ErrorResponse.class)
        );

        step("Verify error message", () -> {
            assertThat(errorResponse.error).isEqualTo("Name must be provided for the project creation");
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Error when retrieving project with invalid id")
    void shouldNotGetProjectWithInvalidId() {
        ErrorResponse errorResponse = step("Create project with invalid id", () ->
                getProjectById(authSpec(), "not_existing_id")
                        .then()
                        .spec(responseSpec(400))
                        .extract()
                        .as(ErrorResponse.class)
        );

        step("Verify error message", () -> {
            assertThat(errorResponse.error).isEqualTo("Invalid argument value");
        });
    }

    @Test
    @Tag("regression")
    @DisplayName("DELETE method is idempotent")
    void shouldDeleteProjectIdempotently() {

        String initialName = "Test Project " + System.currentTimeMillis();

        ProjectResponse createdProject = step("Create project", () ->
                createProject(authSpec(), initialName)
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(ProjectResponse.class)
        );

        step("Delete project first time", () -> {
            deleteProject(authSpec(), createdProject.id)
                    .then()
                    .spec(responseSpec(204));
        });

        step("Delete project second time (idempotent)", () -> {
            deleteProject(authSpec(), createdProject.id)
                    .then()
                    .spec(responseSpec(204));
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Deleted project cannot be retrieved")
    void shouldNotGetDeletedProject() {
        String initialName = "Test Project " + System.currentTimeMillis();

        ProjectResponse createdProject = step("Create project", () ->
                createProject(authSpec(), initialName)
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(ProjectResponse.class)
        );

        step("Delete project", () -> {
            deleteProject(authSpec(), createdProject.id)
                    .then()
                    .spec(responseSpec(204));
        });

        step("Read deleted project", () -> {
            getProjectById(authSpec(), createdProject.id)
                    .then()
                    .spec(responseSpec(404));
        });
    }
}
