package io.github.vikindor.android.tests;

import io.github.vikindor.android.helpers.A;
import io.github.vikindor.android.ui.components.*;
import io.github.vikindor.android.ui.screens.main.InboxScreen;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Epic("Android")
@Feature("Tasks")
@Tag("mobile") @Tag("android") @Tag("tasks") @Tag("regression")
@DisplayName("Tasks")
public class TasksTests extends TestBase {

    InboxScreen inbox() { return new InboxScreen(); }
    QuickAddButton quickAdd() { return new QuickAddButton(); }
    QuickAddItemContainer quickAddItemContainer() { return new QuickAddItemContainer(); }
    CreateItemContainer createItemContainer() { return new CreateItemContainer(); }
    ItemDetailsContainer itemDetailsContainer() { return new ItemDetailsContainer(); }
    AlertDialog alertDialog() { return new AlertDialog(); }
    SnackBar snackBar() { return new SnackBar(); }

    @Test
    @Tag("smoke")
    @DisplayName("Tasks CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteTask() {

        String initialName = "Test Task " + System.currentTimeMillis();
        String newName = initialName + " updated";

        step("Create task", () -> {
            quickAdd().tap();
            quickAddItemContainer()
                    .setMessage(initialName)
                    .tapAdd();
            A.back();
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
            A.back();
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

        String taskName = "Test Task " + System.currentTimeMillis();

        step("Create task", () -> {
            quickAdd().tap();
            quickAddItemContainer()
                    .setMessage(taskName)
                    .tapAdd();
            A.back();
        });

        step("Complete task", () -> {
            inbox().completeTask(taskName);
        });

        step("Verify task completed", () -> {
            snackBar().shouldShowCompletedMessage();
            inbox().shouldNotContainTask(taskName);
        });
    }
}
