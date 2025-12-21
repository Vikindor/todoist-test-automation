package io.github.vikindor.web.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.vikindor.web.configs.ConfigProvider;
import io.github.vikindor.web.helpers.AllureAttach;
import io.github.vikindor.web.helpers.AuthHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

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
    }

    @BeforeAll
    static void cleanAllureResults() {
        try {
            Path results = Paths.get(System.getProperty("user.dir"), "build", "allure-results");

            if (Files.exists(results)) {
                Files.walk(results)
                     .filter(path -> !path.equals(results))
                     .sorted(Comparator.reverseOrder())
                     .map(Path::toFile)
                     .forEach(File::delete);
            }
        } catch (Exception e) {
            System.err.println("Failed to clean allure-results: " + e.getMessage());
        }
    }

    @BeforeEach
    void setUpTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        AuthHelper.login(ConfigProvider.config().todoistEmail(), ConfigProvider.config().todoistPassword());
    }

    @AfterEach
    void tearDown() {
        AllureAttach.screenshot();
        AllureAttach.pageSource();
        AllureAttach.browserConsoleLogs();
        AllureAttach.selenoidVideo();

        closeWebDriver();
    }
}
