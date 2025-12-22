package io.github.vikindor.web.ui.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class DeleteModal {

    private final SelenideElement modal = $("[data-testid='modal-overlay']");

    public void clickDelete() {
        modal.$$("button").findBy(text("Delete")).click();
    }
}
