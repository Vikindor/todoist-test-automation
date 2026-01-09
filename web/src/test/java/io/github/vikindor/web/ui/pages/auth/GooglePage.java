package io.github.vikindor.web.ui.pages.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class GooglePage {

    private final SelenideElement iFrame = $("iframe[src*='accounts.google.com']");

    public GooglePage shouldHaveIFrame() {
        iFrame.shouldBe(visible);
        return this;
    }
}
