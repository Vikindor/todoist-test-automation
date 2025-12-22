package io.github.vikindor.web.ui.pages.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ProjectsPage {

    private final SelenideElement
            projectsOverview = $("[data-testid='personal-projects-overview']"),
            addButton = projectsOverview.$("button[aria-haspopup='menu']"),
            addProjectAction = $(".reactist_menulist").$("[aria-label='Add project']");

    public ProjectsPage openPage() {
        open("/app/projects/active");
        return this;
    }

    public ProjectsPage clickAddButton() {
        addButton.click();
        return this;
    }

    public ProjectsPage clickAddProject() {
        addProjectAction.click();
        return this;
    }
}
