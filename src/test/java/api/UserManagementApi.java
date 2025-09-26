package api;

import models.request.CreateUserRequest;
import models.response.CreateUserResponse;
import models.response.UserResponse;
import models.response.UsersListResponse;
import specs.ResponseSpec;
import static io.restassured.RestAssured.given;

public class UserManagementApi {
    
    public UsersListResponse getUsersList(int page) {
        return given()
                .queryParam("page", page)
            .when()
                .get("/api/users")
            .then()
                .spec(ResponseSpec.successResponseSpec)
                .extract().as(UsersListResponse.class);
    }
    
    public UserResponse getSingleUser(int userId) {
        return given()
            .when()
                .get("/api/users/{id}", userId)
            .then()
                .spec(ResponseSpec.successResponseSpec)
                .extract().as(UserResponse.class);
    }
    
    public CreateUserResponse createUser(CreateUserRequest request) {
        return given()
                .body(request)
            .when()
                .post("/api/users")
            .then()
                .spec(ResponseSpec.createdResponseSpec)
                .extract().as(CreateUserResponse.class);
    }
    
    public void deleteUser(int userId) {
        given()
            .when()
                .delete("/api/users/{id}", userId)
            .then()
                .spec(ResponseSpec.noContentResponseSpec);
    }

    public void getUserNotFound(int userId) {
        given()
            .when()
                .get("/api/users/{id}", userId)
            .then()
                .spec(ResponseSpec.notFoundResponseSpec);
    }
    
    public CreateUserResponse updateUser(int userId, CreateUserRequest request) {
        return given()
                .body(request)
            .when()
                .put("/api/users/{id}", userId)
            .then()
                .spec(ResponseSpec.successResponseSpec)
                .extract().as(CreateUserResponse.class);
    }
}