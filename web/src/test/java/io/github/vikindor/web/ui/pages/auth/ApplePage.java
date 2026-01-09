package io.github.vikindor.web.ui.pages.auth;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class ApplePage {
    public ApplePage shouldBeOpened() {
        webdriver().shouldHave(urlContaining("appleid.apple.com"));
        return this;
    }
}
