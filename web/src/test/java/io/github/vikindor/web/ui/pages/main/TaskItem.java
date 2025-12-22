package io.github.vikindor.web.ui.pages.main;

import com.codeborne.selenide.SelenideElement;

public class TaskItem {

    private final SelenideElement root;

    TaskItem(SelenideElement root) {
        this.root = root;
    }

    void clickEditAction() {
        root.hover();
        root.$("[data-action-hint='task-edit']").click();
    }

    void openMoreMenu() {
        root.hover();
        root.$("[data-testid='more_menu']").click();
    }
}
