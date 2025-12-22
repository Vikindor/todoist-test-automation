package io.github.vikindor.web.ui.pages.main;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TaskEditMenu {

    private final SelenideElement taskEditMenu = $("[aria-label='task edit menu']");

    void clickDelete() {
        new TaskEditMenuItem(taskEditMenu).clickDeleteAction();
    }

}
