package io.github.vikindor.android.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.vikindor.android.configs.ConfigProvider;
import io.github.vikindor.android.utils.AdbDeviceDetector;
import io.github.vikindor.android.utils.ApkUtils;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.github.vikindor.android.configs.MobilePlatform.*;

public class MobileDriver implements WebDriverProvider {

    @NonNull
    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        String platform = System.getProperty(PROPERTY);

        return switch (platform) {
            case EMULATOR_ANDROID -> emulAndroidDriver();
            case REAL_ANDROID -> realAndroidDriver();
            case BS_ANDROID -> bsAndroidDriver();
            default -> throw new AssertionError("Wrong platform name: " + platform);
        };
    }

    private WebDriver emulAndroidDriver() {

        ApkUtils.installSplitApk();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(ANDROID);
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setDeviceName(ConfigProvider.config().deviceName());
        options.setPlatformVersion(ConfigProvider.config().platformVersion());
        options.setAppPackage(ConfigProvider.config().appPackage());
        options.setAppActivity(ConfigProvider.config().appActivity());
        options.setAutoGrantPermissions(false);

        return new AndroidDriver(getServerUrl(), options);
    }

    private WebDriver realAndroidDriver() {

        ApkUtils.installSplitApk();

        AdbDeviceDetector.DeviceInfo adbDetector = AdbDeviceDetector.detectDeviceInfo();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(ANDROID);
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setDeviceName(adbDetector.deviceId);
        options.setPlatformVersion(adbDetector.platformVersion);
        options.setAppPackage(ConfigProvider.config().appPackage());
        options.setAppActivity(ConfigProvider.config().appActivity());
        options.setAutoGrantPermissions(false);

        return new AndroidDriver(getServerUrl(), options);
    }

    private WebDriver bsAndroidDriver() {
        String bsSessionName = System.getProperty("browserstack.session.name",
                ConfigProvider.config().browserstackSessionName());

        UiAutomator2Options options = new UiAutomator2Options();

        options.setApp(ConfigProvider.config().browserstackApp());
        options.setDeviceName(ConfigProvider.config().deviceName());
        options.setPlatformVersion(ConfigProvider.config().platformVersion());
        options.setCapability("project", ConfigProvider.config().browserstackProjectName());
        options.setCapability("build", ConfigProvider.config().browserstackBuildName());
        options.setCapability("name", bsSessionName);

        return new AndroidDriver(getServerUrl(), options);
    }

    private URL getServerUrl() {
        String platform = System.getProperty(PROPERTY);

        try {
            if (platform.equals(BS_ANDROID)) {
                return new URL(String.format(
                        ConfigProvider.config().browserstackUrl(),
                        ConfigProvider.config().browserstackUser(),
                        ConfigProvider.config().browserstackKey()
                ));
            } else if (platform.equals(REAL_ANDROID) || platform.equals(EMULATOR_ANDROID)) {
                return new URI(ConfigProvider.config().appiumUrl()).toURL();
            } else {
                throw new RuntimeException("Wrong platform name");
            }
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
