package api;

import models.request.CreateUserRequest;
import models.response.CreateUserResponse;
import models.response.UserResponse;
import models.response.UsersListResponse;
import specs.RequestSpec;
import specs.ResponseSpec;

import static io.restassured.RestAssured.given;

public class UserManagementApi {

    public UsersListResponse getUsersList(int page) {
        return given()
                .spec(RequestSpec.baseRequestSpec())
                .queryParam("page", page)
                .when()
                .get("/users")
                .then()
                .spec(ResponseSpec.responseSpec(200))
                .extract().as(UsersListResponse.class);
    }

    public UserResponse getSingleUser(int userId) {
        return given()
                .spec(RequestSpec.baseRequestSpec())
                .when()
                .get("/users/{id}", userId)
                .then()
                .spec(ResponseSpec.responseSpec(200))
                .extract().as(UserResponse.class);
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        return given()
                .spec(RequestSpec.baseRequestSpec())
                .body(request)
                .when()
                .post("/users")
                .then()
                .spec(ResponseSpec.responseSpec(201))
                .extract().as(CreateUserResponse.class);
    }

    public void deleteUser(int userId) {
        given()
                .spec(RequestSpec.baseRequestSpec())
                .when()
                .delete("/users/{id}", userId)
                .then()
                .spec(ResponseSpec.responseSpec(204));
    }

    public void getUserNotFound(int userId) {
        given()
                .spec(RequestSpec.baseRequestSpec())
                .when()
                .get("/users/{id}", userId)
                .then()
                .spec(ResponseSpec.responseSpec(404));
    }

    public CreateUserResponse updateUser(int userId, CreateUserRequest request) {
        return given()
                .spec(RequestSpec.baseRequestSpec())
                .body(request)
                .when()
                .put("/users/{id}", userId)
                .then()
                .spec(ResponseSpec.responseSpec(200))
                .extract().as(CreateUserResponse.class);
    }
}