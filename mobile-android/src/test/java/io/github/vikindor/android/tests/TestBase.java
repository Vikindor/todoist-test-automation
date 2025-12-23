package io.github.vikindor.android.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.appium.java_client.android.AndroidDriver;
import io.github.vikindor.android.configs.ConfigProvider;
import io.github.vikindor.android.drivers.MobileDriver;
import io.github.vikindor.android.helpers.AllureAttach;
import io.github.vikindor.android.helpers.AuthHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static io.github.vikindor.android.configs.MobilePlatform.*;

public abstract class TestBase {

    @BeforeAll
    static void setupConfig() {
        Configuration.browser = MobileDriver.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = ConfigProvider.config().timeout();
    }

    @BeforeEach
    void setUpTest(TestInfo testInfo) {
        String browserstackSessionName = testInfo.getDisplayName();
        System.setProperty("browserstack.session.name", browserstackSessionName);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        open();

        AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
        driver.startRecordingScreen();

        AuthHelper.login(ConfigProvider.config().todoistEmail(), ConfigProvider.config().todoistPassword());
    }

    @AfterEach
    void tearDown() {
        String platform = System.getProperty(PROPERTY);
        String sessionId = Selenide.sessionId().toString();

        AllureAttach.screenshot();
        AllureAttach.pageSource();

        if (platform.equals(EMULATOR_ANDROID) || platform.equals(REAL_ANDROID)) {
            AllureAttach.appiumVideo();
        }

        closeWebDriver();

        if (platform.equals(BS_ANDROID) || platform.equals(BS_IOS)) {
            AllureAttach.browserStackVideo(sessionId);
        }
    }
}
