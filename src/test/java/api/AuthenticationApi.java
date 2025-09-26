package api;

import models.request.LoginRequest;
import models.response.ErrorResponse;
import models.response.LoginResponse;
import static io.restassured.RestAssured.given;
import specs.ResponseSpec;

public class AuthenticationApi {
    public LoginResponse login(LoginRequest loginRequest) {
        return given()
                .body(loginRequest)
            .when()
                .post("/api/login")
            .then()
                .spec(ResponseSpec.successResponseSpec)
                .extract().as(LoginResponse.class);
    }

    public ErrorResponse loginWithInvalidData(LoginRequest loginRequest) {
        return given()
                .body(loginRequest)
            .when()
                .post("/api/login")
            .then()
                .spec(ResponseSpec.badRequestResponseSpec)
                .extract().as(ErrorResponse.class);
    }

    public ErrorResponse loginWithNonExistentUser(LoginRequest loginRequest) {
        return given()
                .body(loginRequest)
            .when()
                .post("/api/login")
            .then()
                .spec(ResponseSpec.badRequestResponseSpec)
                .extract().as(ErrorResponse.class);
    }
}
