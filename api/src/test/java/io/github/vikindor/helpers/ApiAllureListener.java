package io.github.vikindor.helpers;

import io.qameta.allure.restassured.AllureRestAssured;

public class ApiAllureListener {
    private ApiAllureListener(){}

    private static final AllureRestAssured FILTER = new AllureRestAssured()
            .setRequestTemplate("request.ftl")
            .setResponseTemplate("response.ftl");

    public static AllureRestAssured filter() {
        return FILTER;
    }
}
