package io.github.vikindor.android.ui.screens.main;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.xpath;

public class BrowseScreen {

    private final SelenideElement
            addButton = $(androidUIAutomator("new UiSelector().description(\"Add\")")),
            addProjectAction = $(androidUIAutomator("new UiSelector().text(\"Add project\")"));

    private SelenideElement projectTitle(String projectName) {
        return $(xpath("//*[@text=\"" + projectName + "\"]"));
    }

    private SelenideElement projectItem(String projectName) {
        return $(xpath("//*[@text=\"" + projectName + "\"]" + "/ancestor::android.view.View[@clickable='true']"));
    }

    public BrowseScreen tapAdd() {
        addButton.click();
        return this;
    }

    public BrowseScreen tapAddProject() {
        addProjectAction.click();
        return this;
    }

    public BrowseScreen shouldHaveProject(String projectName) {
        projectTitle(projectName).shouldBe(visible);
        return this;
    }

    public BrowseScreen shouldNotHaveProject(String projectName) {
        projectTitle(projectName).shouldNotBe(visible);
        return this;
    }

    public BrowseScreen openProject(String projectName) {
        projectItem(projectName).shouldBe(visible).click();
        return this;
    }
}
