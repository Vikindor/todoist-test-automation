package io.github.vikindor.web.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.vikindor.web.helpers.AllureAttach;
import io.github.vikindor.web.helpers.AuthHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.restassured.RestAssured.baseURI;

public class TestBase {

    @BeforeAll
    static void setupConfig() {
        Configuration.remote           = System.getProperty("remoteUrl");
        Configuration.browser          = System.getProperty("browser", "chrome");
        Configuration.browserVersion   = System.getProperty("browserVersion");
        Configuration.browserSize      = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl          = System.getProperty("baseUrl", "https://app.todoist.com");
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        baseURI = System.getProperty("baseUrl", "https://api.todoist.com/api/v1");
    }

    @BeforeEach
    void setUpTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        AllureAttach.screenshot();
        AllureAttach.pageSource();
        AllureAttach.browserConsoleLogs();

        if (Configuration.remote != null) {
            AllureAttach.selenoidVideo();
        }

        closeWebDriver();
    }
}
