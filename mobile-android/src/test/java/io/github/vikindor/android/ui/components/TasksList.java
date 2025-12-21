package io.github.vikindor.android.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.xpath;

public class TasksList {

    private SelenideElement taskTitle(String taskTitle) {
        return $(xpath(
                "//*[@resource-id='com.todoist:id/text' and @text='" + taskTitle + "']"
        ));
    }

    private SelenideElement taskRoot(String taskTitle) {
        return $(xpath(
                "//*[@resource-id='com.todoist:id/text' and @text='" + taskTitle + "']" +
                        "/ancestor::*[@resource-id='com.todoist:id/root']"
        ));
    }

    private SelenideElement taskCheckmark(String taskTitle) {
        return $(xpath(
                "//*[@resource-id='com.todoist:id/text' and @text='" + taskTitle + "']" +
                        "/ancestor::*[@resource-id='com.todoist:id/root']" +
                        "//*[@resource-id='com.todoist:id/checkmark']"
        ));
    }

    public void shouldContainTask(String taskTitle) {
        taskTitle(taskTitle).shouldBe(visible);
    }

    public void shouldNotContainTask(String taskTitle) {
        taskTitle(taskTitle).shouldNotBe(visible);
    }

    public void openTask(String taskTitle) {
        taskRoot(taskTitle).shouldBe(visible).click();
    }

    public void checkmarkTask(String taskTitle) {
        taskCheckmark(taskTitle).shouldBe(visible).click();
    }
}
