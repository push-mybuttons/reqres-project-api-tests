package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpec {

    private static final AllureRestAssured allureFilter = new AllureRestAssured()
            .setRequestTemplate("http-request.ftl")
            .setResponseTemplate("http-response.ftl")
            .setRequestAttachmentName("Request")
            .setResponseAttachmentName("Response");

    public static RequestSpecification baseRequestSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("x-api-key", "reqres-free-v1")
                .addFilter(allureFilter)
                .build();
    }

    public static RequestSpecification requestSpecWithBody(Object body) {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseRequestSpec())
                .setBody(body)
                .build();
    }
}

