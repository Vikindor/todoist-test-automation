package io.github.vikindor.web.api;

import io.github.vikindor.web.configs.ProjectConfig;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TodoistAuthApi {

    private static final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    public static Response login(int accountIndex) {
        AuthData authData = new AuthData(
                config.todoistEmail(accountIndex),
                config.todoistPassword(accountIndex),
                config.todoistDeviceId(accountIndex)
        );

        return given()
                .contentType(JSON)
                .body(authData)
                .post("/user/login");
    }
}
