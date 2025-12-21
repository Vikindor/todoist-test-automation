package io.github.vikindor.web.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Web")
@Feature("Tasks")
@Tag("web") @Tag("tasks") @Tag("regression")
@DisplayName("Tasks")
public class TasksTests {

    @Test
    @Tag("smoke")
    @DisplayName("Tasks CRUD lifecycle")
    void shouldCreateReadUpdateAndDeleteTask() {

    }
}
