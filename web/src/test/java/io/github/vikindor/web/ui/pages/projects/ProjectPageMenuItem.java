package io.github.vikindor.web.ui.pages.projects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;

public class ProjectPageMenuItem {

    private final SelenideElement root;

    public ProjectPageMenuItem(SelenideElement root) {
        this.root = root;
    }

    void clickEditAction() { root.$$("[role='menuitem']").findBy(text("Edit")).click(); }

    void clickDeleteAction() { root.$$("[role='menuitem']").findBy(text("Delete")).click(); }
}
