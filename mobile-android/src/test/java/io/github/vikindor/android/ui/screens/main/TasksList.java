package io.github.vikindor.android.ui.screens.main;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.xpath;

public class TasksList {

    private SelenideElement taskName(String taskName) {
        return $(xpath(
                "//*[@resource-id='com.todoist:id/text' and @text='" + taskName + "']"
        ));
    }

    private SelenideElement taskRoot(String taskName) {
        return $(xpath(
                "//*[@resource-id='com.todoist:id/text' and @text='" + taskName + "']" +
                        "/ancestor::*[@resource-id='com.todoist:id/root']"
        ));
    }

    private SelenideElement taskCheckmark(String taskName) {
        return $(xpath(
                "//*[@resource-id='com.todoist:id/text' and @text='" + taskName + "']" +
                        "/ancestor::*[@resource-id='com.todoist:id/root']" +
                        "//*[@resource-id='com.todoist:id/checkmark']"
        ));
    }

    void shouldContainTask(String taskName) { taskName(taskName).shouldBe(visible); }

    void shouldNotContainTask(String taskName) {
        taskName(taskName).shouldNotBe(visible);
    }

    void openTask(String taskName) {
        taskRoot(taskName).shouldBe(visible).click();
    }

    void checkmarkTask(String taskName) {
        taskCheckmark(taskName).shouldBe(visible).click();
    }
}
