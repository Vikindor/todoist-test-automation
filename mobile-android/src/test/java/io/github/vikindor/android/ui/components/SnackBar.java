package io.github.vikindor.android.ui.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.xpath;

public class SnackBar {

    public SnackBar shouldShowCompletedMessage() {
        $(xpath(
                "//*[@resource-id='com.todoist:id/compose_snackbar']" +
                        "//*[contains(@text, 'Completed')]"
        )).shouldBe(visible);

        return this;
    }

    public SnackBar shouldShowTaskCharacterLimitError() {
        $(xpath(
                "//*[@resource-id='com.todoist:id/snackbar_text' " +
                        "and contains(@text, 'Task name character limit:')]"
        )).shouldBe(visible);

        return this;
    }
}
