package com.example.api;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ObjectTest extends BaseTest {

    @BeforeClass
    public void setup() {
        loginAndSetToken();
    }

    @Test
    public void testGetAllObjects() {
        Response response = RestAssured
            .given()
                .header("Authorization", "Bearer " + token)
            .when()
                .get(baseUrl + "/webhook/api/objects");

        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testAddObject() {
        String requestBody = "{ \"name\": \"Macbook Red\", \"data\": {" +
            "\"year\":2024," +
            "\"price\":1999.99," +
            "\"cpu_model\":\"M3 Pro\"," +
            "\"hard_disk_size\":\"1 TB\"," +
            "\"capacity\":\"2 cpu\"," +
            "\"screen_size\":\"14 Inch\"," +
            "\"color\":\"red\"" +
        "}}";

        Response response = RestAssured
            .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post(baseUrl + "/webhook/api/objects");

        assertEquals(response.statusCode(), 200);

        // Parsing id yang bisa jadi string array atau integer langsung
        Object idObj = response.jsonPath().get("id");
        int id = -1;

        if (idObj instanceof Integer) {
            id = (Integer) idObj;
        } else if (idObj instanceof String) {
            // kalau bentuknya string seperti "[251]", hapus kurung siku dulu lalu parse
            String idStr = ((String) idObj).replaceAll("[\\[\\]]", "");
            id = Integer.parseInt(idStr);
        } else if (idObj instanceof List) {
            // jika id berupa list, ambil element pertama
            List<?> idList = (List<?>) idObj;
            if (!idList.isEmpty()) {
                id = Integer.parseInt(idList.get(0).toString());
            }
        }

        assertTrue(id > 0, "Id should be greater than 0");
    }

    @Test
    public void testDeleteObject() {
        int deleteId = 137; // pastikan ID valid dan ada di server

        Response response = RestAssured
            .given()
                .header("Authorization", "Bearer " + token)
            .when()
                .delete(baseUrl + "/webhook/api/objects/" + deleteId);

        assertEquals(response.statusCode(), 200); // atau 204 tergantung API
    }

    // Test tambah objek baru lalu hapus objek tersebut (untuk menghindari error 404)
    @Test
    public void testAddAndDeleteObject() {
        String requestBody = "{ \"name\": \"Macbook Blue\", \"data\": {" +
            "\"year\":2025," +
            "\"price\":2499.99," +
            "\"cpu_model\":\"M3 Max\"," +
            "\"hard_disk_size\":\"2 TB\"," +
            "\"capacity\":\"4 cpu\"," +
            "\"screen_size\":\"16 Inch\"," +
            "\"color\":\"blue\"" +
        "}}";

        // Tambah objek
        Response addResponse = RestAssured
            .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post(baseUrl + "/webhook/api/objects");

        assertEquals(addResponse.statusCode(), 200);

        // Ambil ID objek baru
        Object idObj = addResponse.jsonPath().get("id");
        int id = -1;

        if (idObj instanceof Integer) {
            id = (Integer) idObj;
        } else if (idObj instanceof String) {
            String idStr = ((String) idObj).replaceAll("[\\[\\]]", "");
            id = Integer.parseInt(idStr);
        } else if (idObj instanceof List) {
            List<?> idList = (List<?>) idObj;
            if (!idList.isEmpty()) {
                id = Integer.parseInt(idList.get(0).toString());
            }
        }

        assertTrue(id > 0, "Id should be greater than 0");

        // Hapus objek berdasarkan ID
        Response deleteResponse = RestAssured
            .given()
                .header("Authorization", "Bearer " + token)
            .when()
                .delete(baseUrl + "/webhook/api/objects/" + id);

        assertEquals(deleteResponse.statusCode(), 200); // atau 204 sesuai API
    }
}
