package io.github.vikindor.android.helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public final class Android {

    private Android() {
    }

    private static AndroidDriver driver() {
        return (AndroidDriver) WebDriverRunner.getWebDriver();
    }

    public static String getCurrentPackage() {
        return driver().getCurrentPackage();
    }

    public static void back() {
        driver().pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public static void shouldHaveToast(String text) {
        Selenide.Wait().until(driver -> driver.getPageSource().contains(text));
    }

    public static void waitForExternalAppOpen(String packageName) {
        Selenide.Wait().until(driver -> !getCurrentPackage().equals(packageName));
    }
}
