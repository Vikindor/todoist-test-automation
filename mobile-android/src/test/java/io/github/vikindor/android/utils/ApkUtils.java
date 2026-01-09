package io.github.vikindor.android.utils;

import io.github.vikindor.android.configs.ConfigProvider;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ApkUtils {
    private static final String APPS_DIR = "mobile-android/src/test/resources/app/";

    // For single-file APKs ("app" property = APK file name, e.g. todoist.apk)
    public String getApkPath() {
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
    public static void installSplitApk() {
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
}
