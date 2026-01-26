package io.github.vikindor.web.ui.pages.auth;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class FacebookPage {

    private final SelenideElement facebookLoginTitle = $$("div").findBy(exactText("Log in to Facebook"));

    public FacebookPage shouldHaveTitle() {
        facebookLoginTitle.shouldBe(visible);
        return this;
    }
}
