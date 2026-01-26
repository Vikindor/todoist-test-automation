package io.github.vikindor.web.helpers;

import io.github.vikindor.web.api.TodoistAuthApi;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AuthHelper {

    public static void apiLoginAndStabilize(int accountIndex) {
        Response response = TodoistAuthApi.login(accountIndex);
        applyAuthCookies(response);
        stabilizeAuthenticatedApp();
    }

    private static void applyAuthCookies(Response loginResponse) {
        open("/favicon.ico");

        Map<String, String> cookies = loginResponse.getCookies();

        cookies.forEach((name, value) -> {
            if (isAuthCookie(name)) {
                getWebDriver().manage().addCookie(
                        new Cookie.Builder(name, value)
                                .domain(".todoist.com")
                                .path("/")
                                .isSecure(true)
                                .isHttpOnly(true)
                                .build()
                );
            }
        });
    }

    private static boolean isAuthCookie(String name) {
        return name.equals("tduser") || name.equals("todoistd");
    }

    private static void stabilizeAuthenticatedApp() {
        // Technical navigation to allow SPA auth context to finalize
        open("/app/inbox");

        // Todoist completes authentication asynchronously.
        // No reliable DOM, cookie or network signal exists.
        // Fixed delay acts as a synchronization barrier.
        sleep(1000);
    }
}
