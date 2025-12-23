package io.github.vikindor.web.ui.pages.projects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProjectsPage {

    private final SelenideElement
            projectsOverview = $("[data-testid='personal-projects-overview']"),
            projectsList = projectsOverview.$("ul[aria-label='Projects']"),
            addButton = projectsOverview.$("button[aria-haspopup='menu']"),
            addProjectAction = $(".reactist_menulist").$("[aria-label='Add project']");

    private final ElementsCollection projectRow = projectsList.$$("[data-testid='project-row']");

    public ProjectsPage openPage() {
        open("/app/projects/active");
        projectsOverview.shouldBe(visible);
        return this;
    }

    public ProjectsPage shouldHaveProject(String projectName) {
        projectRow.filterBy(text(projectName)).shouldHave(size(1));
        return this;
    }

    public ProjectsPage shouldNotHaveProject(String projectName) {
        projectRow.filterBy(text(projectName)).shouldHave(size(0));
        return this;
    }

    public ProjectsPage clickAddButton() {
        addButton.shouldBe(visible).click();
        return this;
    }

    public ProjectsPage clickAddProject() {
        addProjectAction.shouldBe(visible).click();
        return this;
    }
}
