package io.github.vikindor.android.tests;

import io.github.vikindor.android.extensions.WithLogin;
import io.github.vikindor.android.helpers.Android;
import io.github.vikindor.android.testdata.GeneratedData;
import io.github.vikindor.android.ui.actions.TaskActions;
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
@Feature("Tasks")
@Tag("mobile") @Tag("android") @Tag("tasks") @Tag("regression")
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
            quickAdd().tap();
            quickAddItemContainer()
                    .setTaskName(initialName)
                    .tapAdd();
            Android.back();
        });

        step("Read task and verify actual name", () -> {
            inbox().shouldContainTask(initialName);
        });

        step("Update task and verify updated name", () -> {
            inbox().openTask(initialName);
            createItemContainer().tapTaskName();
            itemDetailsContainer()
                    .setTitle(newName)
                    .tapSave();
            Android.back();
            inbox().shouldContainTask(newName);
        });

        step("Delete task and verify it is deleted", () -> {
            inbox().openTask(newName);
            createItemContainer()
                    .tapOverflowButton()
                    .tapDeleteTask();
            alertDialog().tapDelete();
            inbox().shouldNotContainTask(newName);
        });
    }

    @Test
    @Tag("smoke")
    @DisplayName("Task can be completed")
    void shouldCompleteTask() {
        String taskName = GeneratedData.nameOfLength(10);

        step("Create task", () -> {
            quickAdd().tap();
            quickAddItemContainer()
                    .setTaskName(taskName)
                    .tapAdd();
            Android.back();
        });

        step("Complete task", () -> {
            inbox().completeTask(taskName);
        });

        step("Verify task completed", () -> {
            snackBar().shouldShowCompletedMessage();
            inbox().shouldNotContainTask(taskName);
        });
    }

    @ParameterizedTest
    @Tag("regression")
    @MethodSource("io.github.vikindor.android.testdata.TaskNameTestData#validNames")
    @DisplayName("Tasks with different valid names can be created")
    void shouldCreateTasksWithDifferentValidNames(String taskName) {
        step("Create task", () -> {
            quickAdd().tap();
            quickAddItemContainer()
                    .setTaskName(taskName)
                    .tapAdd();
            Android.back();
        });

        step("Verify task name", () -> {
            inbox().shouldContainTask(taskName);
        });

        TaskActions.taskCleanup(taskName);
    }

    @Test
    @Tag("regression")
    @DisplayName("Task name supports ASCII symbols")
    void shouldCreateTaskWithAsciiSymbols() {
        String taskName = "!@#$%^&*()_+-={}[]";

        step("Create task", () -> {
            quickAdd().tap();
            quickAddItemContainer()
                    .setTaskName(taskName)
                    .tapAdd();
            Android.back();
        });

        step("Verify task name", () -> {
            inbox().shouldContainTask(taskName);
        });

        TaskActions.taskCleanup(taskName);
    }

    @Test
    @Tag("regression")
    @DisplayName("Task name supports Unicode symbols")
    void shouldCreateTaskWithUnicodeSymbols() {
        String taskName = "№✓★";

        step("Create task", () -> {
            quickAdd().tap();
            quickAddItemContainer()
                    .setTaskName(taskName)
                    .tapAdd();
            Android.back();
        });

        step("Verify task name", () -> {
            inbox().shouldContainTask(taskName);
        });

        TaskActions.taskCleanup(taskName);
    }

    @Test
    @Tag("regression")
    @DisplayName("Snackbar is shown when task name exceeds character limit")
    void shouldShowSnackBarWhenTaskNameExceedsCharacterLimit() {
        String taskName = GeneratedData.nameOfLength(501);

        step("Paste task name longer than allowed limit", () -> {
            quickAdd().tap();
            Android.setClipboardText(taskName);
            Android.paste();
        });

        step("Verify character limit error", () -> {
            snackBar().shouldShowTaskCharacterLimitError();
        });
    }
}
