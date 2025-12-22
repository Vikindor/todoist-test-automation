package io.github.vikindor.web.ui.pages.main;

import com.codeborne.selenide.SelenideElement;

public class TaskEditMenuItem {

    private final SelenideElement root;

    TaskEditMenuItem(SelenideElement root) { this.root = root; }

    void clickDeleteAction() { root.$("[data-action-hint='task-overflow-menu-delete']").click(); }
}
