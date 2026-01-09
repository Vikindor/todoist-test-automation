package io.github.vikindor.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String USER = "/user";

    public static Response getUser(RequestSpecification spec) {
        return given()
                .spec(spec)
                .when()
                .get(USER);
    }
}
