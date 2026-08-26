package io.github.vikindor.tests;

import io.github.vikindor.helpers.ApiAllureListener;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void setupApi() {
        RestAssured.baseURI = System.getProperty("baseUrl", "https://api.todoist.com");
        RestAssured.basePath = "/api/v1";
        RestAssured.filters(ApiAllureListener.filter());
    }
}
