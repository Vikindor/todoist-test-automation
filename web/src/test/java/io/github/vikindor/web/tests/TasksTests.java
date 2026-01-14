package io.github.vikindor.web.tests;

import io.github.vikindor.web.extensions.WithLogin;
import io.github.vikindor.web.testdata.GeneratedData;
import io.github.vikindor.web.ui.actions.TaskActions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static io.qameta.allure.Allure.step;
import static io.github.vikindor.web.ui.pages.Pages.*;

@Epic("Web")
@Feature("Tasks")
@Tag("web") @Tag("tasks") @Tag("regression")
@DisplayName("Tasks")
@WithLogin
public class TasksTests extends TestBase {

    @Test
    @Tag("smoke")
    @DisplayName("Tasks CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteTask() {
        String initialName = GeneratedData.nameOfLength(10);
        String newName = initialName + " updated";

        step("Create task", () -> {
            inbox().clickAddTaskButton();
            quickAdd()
                    .setTaskName(initialName)
                    .clickSubmitButton();
        });

        step("Read task and verify actual name", () -> {
            inbox().shouldContainTask(initialName);
        });

        step("Update task and verify updated name", () -> {
            inbox()
                    .editTask(initialName)
                    .setTaskName(newName)
                    .saveTaskChanges()
                    .shouldContainTask(newName);
        });

        step("Delete task and verify it is deleted", () -> {
            inbox()
                    .openTaskEditMenu(newName)
                    .deleteTask();
            deleteTaskModal().clickDelete();
            inbox().shouldNotContainTask(newName);
        });
    }

    @Test
    @Tag("smoke")
    @DisplayName("Task can be completed")
    void shouldCompleteTask() {
        String taskName = GeneratedData.nameOfLength(10);

        step("Create task", () -> {
            inbox().clickAddTaskButton();
            quickAdd()
                    .setTaskName(taskName)
                    .clickSubmitButton();
        });

        step("Complete task", () -> {
            inbox().completeTask(taskName);
        });

        step("Verify task completed", () -> {
            inbox().shouldNotContainTask(taskName);
        });
    }

    @ParameterizedTest
    @Tag("regression")
    @MethodSource("io.github.vikindor.web.testdata.TaskNameTestData#validNames")
    @DisplayName("Tasks with different valid names can be created")
    void shouldCreateTasksWithDifferentValidNames(String taskName) {
        step("Create task", () -> {
            inbox().clickAddTaskButton();
            quickAdd()
                    .setTaskName(taskName)
                    .clickSubmitButton();
        });

        step("Verify task name", () -> {
            inbox().shouldContainTask(taskName);
        });

        TaskActions.taskCleanup(taskName);
    }

    @Test
    @Tag("regression")
    @DisplayName("Submit button is disabled when task name is blank")
    void shouldNotEnableSubmitButtonWhenNameIsBlank() {
        String taskName = " ";

        step("Initiate task creation and verify submit button is disabled by default", () -> {
            inbox().clickAddTaskButton();
            quickAdd().shouldHaveDisabledSubmitButton();
        });

        step("Enter blank name and verify submit button is disabled", () -> {
            quickAdd()
                    .setTaskName(taskName)
                    .shouldHaveDisabledSubmitButton();
        });
    }

    @Test
    @Tag("regression")
    @DisplayName("Submit button is disabled when task name exceeds character limit")
    void shouldDisableSubmitWhenTaskNameExceedsCharacterLimit() {
        String taskName = GeneratedData.nameOfLength(501);

        step("Enter task name longer than allowed limit", () -> {
            inbox().clickAddTaskButton();
            quickAdd().setTaskName(taskName);
        });

        step("Verify character limit error and disabled submit button", () -> {
            quickAdd()
                    .shouldShowCharacterLimitError()
                    .shouldHaveDisabledSubmitButton();
        });
    }
}
