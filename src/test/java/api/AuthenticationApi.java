package api;

import models.request.LoginRequest;
import models.response.ErrorResponse;
import models.response.LoginResponse;
import specs.RequestSpec;
import specs.ResponseSpec;

import static io.restassured.RestAssured.given;

public class AuthenticationApi {
    public LoginResponse login(LoginRequest loginRequest) {
        return given()
                .spec(RequestSpec.baseRequestSpec())
                .body(loginRequest)
                .when()
                .post("/login")
                .then()
                .spec(ResponseSpec.responseSpec(200))
                .extract().as(LoginResponse.class);
    }

    public ErrorResponse loginWithInvalidData(LoginRequest loginRequest) {
        return given()
                .spec(RequestSpec.baseRequestSpec())
                .body(loginRequest)
                .when()
                .post("/login")
                .then()
                .spec(ResponseSpec.responseSpec(400))
                .extract().as(ErrorResponse.class);
    }

    public ErrorResponse loginWithNonExistentUser(LoginRequest loginRequest) {
        return given()
                .spec(RequestSpec.baseRequestSpec())
                .body(loginRequest)
                .when()
                .post("/login")
                .then()
                .spec(ResponseSpec.responseSpec(400))
                .extract().as(ErrorResponse.class);
    }
}
