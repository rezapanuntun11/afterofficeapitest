package com.example.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthTest extends BaseTest {

    @Test
    public void testLoginSuccess() {
        loginAndSetToken();
        assertNotNull(token, "Token harus ada setelah login berhasil");
    }

    @Test
    public void testRegister() {
        String email = "user" + System.currentTimeMillis() + "@mail.com";

        Response response = RestAssured
            .given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"" + email + "\", " +
                      "\"full_name\": \"Albert Simanjuntak\", " +
                      "\"password\": \"@dmin123\", " +
                      "\"department\": \"finance\", " +
                      "\"phone_number\": \"081234567890\"}")
            .when()
                .post(baseUrl + "/webhook/api/register");

        assertEquals(response.statusCode(), 200, "Status code harus 200");
    }
}
