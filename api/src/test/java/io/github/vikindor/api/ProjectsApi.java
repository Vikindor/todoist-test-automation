package io.github.vikindor.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectsApi {
    private static final String PROJECTS = "/projects";
    private static final String PROJECT_BY_ID = "/projects/{id}";

    public static Response getProjects(RequestSpecification spec) {
        return given()
                .spec(spec)
                .when()
                .get(PROJECTS);
    }

    public static Response getProjectById(RequestSpecification spec, String projectId) {
        return given()
                .spec(spec)
                .when()
                .get(PROJECT_BY_ID, projectId);
    }

    public static Response createProject(RequestSpecification spec, String name) {
        return given()
                .spec(spec)
                .body(Map.of("name", name))
                .when()
                .post(PROJECTS);
    }

    public static Response updateProject(RequestSpecification spec, String projectId, String newName) {
        return given()
                .spec(spec)
                .body(Map.of("name", newName))
                .when()
                .post(PROJECT_BY_ID, projectId);
    }

    public static Response deleteProject(RequestSpecification spec, String projectId) {
        return given()
                .spec(spec)
                .when()
                .delete(PROJECT_BY_ID, projectId);
    }
}
