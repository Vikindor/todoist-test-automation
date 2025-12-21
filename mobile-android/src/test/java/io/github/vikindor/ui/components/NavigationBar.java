package io.github.vikindor.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;

public class NavigationBar {

    private static final SelenideElement
            inboxButton = $(androidUIAutomator("new UiSelector().resourceId(\"test_tag_inbox\")")),
            browseButton = $(androidUIAutomator("new UiSelector().resourceId(\"test_tag_navigation\")"));

    public NavigationBar tapInbox() {
        inboxButton.click();
        return this;
    }

    public NavigationBar tapBrowse() {
        browseButton.click();
        return this;
    }
}
