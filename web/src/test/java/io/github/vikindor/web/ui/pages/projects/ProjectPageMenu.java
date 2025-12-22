package io.github.vikindor.web.ui.pages.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectPageMenu {

    private final SelenideElement projectEditMenu = $("[class='reactist_menulist']");

    void clickEdit(){
        new ProjectPageMenuItem(projectEditMenu).clickEditAction();
    }

    void clickDelete() {
        new ProjectPageMenuItem(projectEditMenu).clickDeleteAction();
    }
}
