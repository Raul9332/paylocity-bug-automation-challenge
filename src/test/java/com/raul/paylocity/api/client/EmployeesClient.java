package com.raul.paylocity.api.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeesClient {
    private final String baseUrl;
    private final String authHeaderValue;

    public EmployeesClient(String baseUrl, String authHeaderValue) {
        this.baseUrl = baseUrl;
        this.authHeaderValue = authHeaderValue;
    }

    private RequestSpecification spec() {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header("Authorization", authHeaderValue)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public Response getAll() {
        return spec().get("/api/Employees");
    }

    public Response getById(String id) {
        return spec().get("/api/Employees/" + id);
    }

    public Response create(Object employeeBody) {
        return spec().body(employeeBody).post("/api/Employees");
    }

    public Response update(Object employeeBody) {
        return spec().body(employeeBody).put("/api/Employees");
    }

    public Response delete(String id) {
        return spec().delete("/api/Employees/" + id);
    }
}
