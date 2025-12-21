package io.github.vikindor.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.github.vikindor.configs.ConfigProvider;
import io.github.vikindor.utils.AdbDeviceDetector;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.github.vikindor.configs.MobilePlatform.*;

public class MobileDriver implements WebDriverProvider {

    private static final String APPS_DIR = "mobile-android/src/test/resources/app/";

    @NonNull
    @Override
    public WebDriver createDriver(@NonNull Capabilities capabilities) {
        String platform = System.getProperty(PROPERTY);

        return switch (platform) {
            case EMULATOR_ANDROID -> emulAndroidDriver();
            case REAL_ANDROID -> realAndroidDriver();
            case BS_ANDROID -> bsAndroidDriver();
            case BS_IOS -> bsIOSDriver();
            default -> throw new AssertionError("Wrong platform name: " + platform);
        };
    }

    private WebDriver emulAndroidDriver() {
        installSplitApk();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(ANDROID);
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setDeviceName(ConfigProvider.config().deviceName());
        options.setPlatformVersion(ConfigProvider.config().platformVersion());
//        options.setApp(getAppPath());
        options.setAppPackage(ConfigProvider.config().appPackage());
        options.setAppActivity(ConfigProvider.config().appActivity());
        options.setAutoGrantPermissions(false);

        return new AndroidDriver(getServerUrl(), options);
    }

    private WebDriver realAndroidDriver() {
        installSplitApk();

        AdbDeviceDetector.DeviceInfo adbDetector = AdbDeviceDetector.detectDeviceInfo();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(ANDROID);
        options.setAutomationName(ANDROID_UIAUTOMATOR2);
        options.setDeviceName(adbDetector.deviceId);
        options.setPlatformVersion(adbDetector.platformVersion);
//        options.setApp(getAppPath());
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

    private WebDriver bsIOSDriver() {
        String bsSessionName = System.getProperty("browserstack.session.name",
                ConfigProvider.config().browserstackSessionName());

        XCUITestOptions options = new XCUITestOptions();

        options.setApp(ConfigProvider.config().browserstackApp());
        options.setDeviceName(ConfigProvider.config().deviceName());
        options.setPlatformVersion(ConfigProvider.config().platformVersion());
        options.setCapability("project", ConfigProvider.config().browserstackProjectName());
        options.setCapability("build", ConfigProvider.config().browserstackBuildName());
        options.setCapability("name", bsSessionName);

        return new IOSDriver(getServerUrl(), options);
    }

    // For single-file APKs ("app" property = APK file name, e.g. todoist.apk)
    private String getApkPath() {
        File app = Paths.get(
                System.getProperty("user.dir"),
                APPS_DIR,
                ConfigProvider.config().app()
        ).toFile();

        if (!app.exists()) {
            throw new AssertionError("App not found: " + app.getAbsolutePath());
        }

        return app.getAbsolutePath();
    }

    // For unpacked split APK bundles ("app" property = directory name, e.g. todoist)
    private void installSplitApk() {
        Path appDir = Paths.get(
                System.getProperty("user.dir"),
                APPS_DIR,
                ConfigProvider.config().app()
        );

        if (!Files.exists(appDir)) {
            throw new AssertionError("App directory not found: " + appDir);
        }

        try {
            List<String> command = new ArrayList<>();
            command.add("adb");
            command.add("install-multiple");
            command.add("-r");

            Files.list(appDir)
                 .filter(p -> p.toString().endsWith(".apk"))
                 .forEach(p -> command.add(p.toAbsolutePath().toString()));

            Process process = new ProcessBuilder(command)
                    .redirectErrorStream(true)
                    .start();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Failed to install split APKs");
            }

        } catch (Exception e) {
            throw new RuntimeException("Error installing split APKs", e);
        }
    }

    private URL getServerUrl() {
        String platform = System.getProperty(PROPERTY);

        try {
            if (platform.equals(BS_ANDROID) || platform.equals(BS_IOS)) {
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
