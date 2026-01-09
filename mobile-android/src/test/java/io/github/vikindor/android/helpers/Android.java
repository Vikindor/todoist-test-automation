package io.github.vikindor.android.helpers;

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
}
