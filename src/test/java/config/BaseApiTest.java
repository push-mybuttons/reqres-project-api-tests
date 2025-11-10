package config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "https://reqres.in");
        RestAssured.basePath = "/api";
    }
}