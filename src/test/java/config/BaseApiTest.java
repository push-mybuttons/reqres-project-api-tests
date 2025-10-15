package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;


public class BaseApiTest {

    private static final AllureRestAssured allureFilter = new AllureRestAssured()
            .setRequestTemplate("http-request.ftl")
            .setResponseTemplate("http-response.ftl")
            .setRequestAttachmentName("Request")
            .setResponseAttachmentName("Response");

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "https://reqres.in");
        RestAssured.basePath = "/api";

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key", "reqres-free-v1")
                .addFilter(allureFilter)
                .build();

        RestAssured.requestSpecification = requestSpec;
    }
}