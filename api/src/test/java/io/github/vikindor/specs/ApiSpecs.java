package io.github.vikindor.specs;

import io.github.vikindor.helpers.TokenProvider;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.github.vikindor.helpers.ApiAllureListener.withCustomTemplate;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class ApiSpecs {

    public static RequestSpecification baseSpec() {
        return with()
                .filter(withCustomTemplate())
                .log().method()
                .log().uri()
                .log().headers()
                .log().body()
                .contentType(JSON);
    }

    public static RequestSpecification authSpec() {
        return baseSpec()
                .header("Authorization", "Bearer " + TokenProvider.getToken());
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(STATUS)
                .log(BODY)
                .build();
    }
}
