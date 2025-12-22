package io.github.vikindor.web.tests;

import io.github.vikindor.web.ui.components.DeleteModal;
import io.github.vikindor.web.ui.components.QuickAddModal;
import io.github.vikindor.web.ui.pages.main.InboxPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Epic("Web")
@Feature("Tasks")
@Tag("web") @Tag("tasks") @Tag("regression")
@DisplayName("Tasks")
public class TasksTests extends TestBase {

    InboxPage inbox() { return new InboxPage(); }
    QuickAddModal quickAdd() { return new QuickAddModal(); }
    DeleteModal deleteTaskModal() { return new DeleteModal(); }

    @Test
    @Tag("smoke")
    @DisplayName("Tasks CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteTask() {

        String initialName = "Test Task " + System.currentTimeMillis();
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

        String taskName = "Test Task " + System.currentTimeMillis();

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
}
