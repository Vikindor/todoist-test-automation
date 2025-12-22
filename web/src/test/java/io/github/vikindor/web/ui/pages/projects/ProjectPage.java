package io.github.vikindor.web.ui.pages.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    private final SelenideElement
            projectOverview = $("[data-testid='project-view']"),
            projectTitleText = projectOverview.$("[data-testid='large-header'] h1"),
            projectOptionsButton = projectOverview.$("[aria-label='Project options menu']");

    private final ProjectPageMenu projectPageMenu = new ProjectPageMenu();

    public ProjectPage shouldHaveProjectName(String projectName) {
        projectTitleText.shouldHave(text(projectName)).shouldBe(visible);
        return this;
    }

    public ProjectPage shouldNotHaveProjectName(String projectName) {
        projectTitleText.shouldNotHave(text(projectName)).shouldBe(visible);
        return this;
    }

    public ProjectPage openProjectOptionsMenu() {
        projectOptionsButton.click();
        return this;
    }

    public ProjectPage editProject() {
        projectPageMenu.clickEdit();
        return this;
    }

    public ProjectPage deleteProject() {
        projectPageMenu.clickDelete();
        return this;
    }
}
