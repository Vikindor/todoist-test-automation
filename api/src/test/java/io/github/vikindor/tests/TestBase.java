package io.github.vikindor.tests;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class TestBase {

    @BeforeAll
    static void setupApi() {
        baseURI = System.getProperty("baseUrl", "https://api.todoist.com/api/v1");
    }
}
