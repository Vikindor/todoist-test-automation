package io.github.vikindor.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.github.vikindor.api.UserApi.getUser;
import static io.github.vikindor.specs.ApiSpecs.*;

@Epic("Todoist API")
@Feature("Authorization")
@Tag("api") @Tag("auth") @Tag("regression")
@DisplayName("Authorization")
public class AuthorizationTests extends TestBase {

    @Test
    @Tag("smoke")
    @DisplayName("API is accessible with valid auth token")
    void shouldAllowAccessWithValidAuthToken() {
        getUser(authSpec())
                .then()
                .spec(responseSpec(200));
    }

    @Test
    @Tag("negative")
    @DisplayName("API access is denied without auth token")
    void shouldDenyAccessWithoutAuthToken() {
        getUser(baseSpec())
                .then()
                .spec(responseSpec(401));
    }

    @Test
    @Tag("negative")
    @DisplayName("API access is denied with invalid auth token")
    void shouldDenyAccessWithInvalidAuthToken() {
        getUser(baseSpec().header("Authorization", "Bearer invalid"))
                .then()
                .spec(responseSpec(401));
    }
}
