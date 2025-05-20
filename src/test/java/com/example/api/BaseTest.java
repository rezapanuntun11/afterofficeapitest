package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseTest {
    protected String baseUrl = "https://whitesmokehouse.com";
    protected String token;

    protected void loginAndSetToken() {
        Response response = RestAssured
            .given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"albertsimanjuntak12@gmail.com\", \"password\": \"@dmin123\"}")
            .when()
                .post(baseUrl + "/webhook/api/login");

        token = response.jsonPath().getString("token");
    }
}
