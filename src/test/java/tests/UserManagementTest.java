package tests;

import api.UserManagementApi;
import config.BaseApiTest;
import io.qameta.allure.*;
import models.request.CreateUserRequest;
import models.response.CreateUserResponse;
import models.response.UserData;
import models.response.UserResponse;
import models.response.UsersListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static helpers.TestData.Users.*;

@Epic("Reqres API") 
@Feature("User Management")
@Story("User Operations")
@Owner("MariiaP")
public class UserManagementTest extends BaseApiTest {

    private final UserManagementApi userApi = new UserManagementApi();

    @Test
    @DisplayName("Получение списка пользователей с первой страницы")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    void getUsersListFirstPageTest() {
        UsersListResponse response = step("Получение списка пользователей с 1 страницы", () -> 
            userApi.getUsersList(1)
        );

        step("Проверка метаданных пагинации", () -> {
            assertThat(response.getPage()).isEqualTo(1);
            assertThat(response.getPerPage()).isEqualTo(6);
            assertThat(response.getTotal()).isEqualTo(12);
            assertThat(response.getTotalPages()).isEqualTo(2);
        });

        step("Проверка количества пользователей", () -> {
            assertThat(response.getData()).hasSize(6);
        });

        step("Проверка конкретного первого пользователя", () -> {
            UserData firstUser = response.getData().get(0);
            assertThat(firstUser.getId()).isEqualTo(1);
            assertThat(firstUser.getEmail()).isEqualTo("george.bluth@reqres.in");
            assertThat(firstUser.getFirstName()).isEqualTo("George");
            assertThat(firstUser.getLastName()).isEqualTo("Bluth");
            assertThat(firstUser.getAvatar()).isEqualTo("https://reqres.in/img/faces/1-image.jpg");
        });
    }

    @ParameterizedTest(name = "Получение пользователя: ID={0}, Email={1}, Имя={2}")
    @DisplayName("Получение конкретных пользователей с проверкой данных")
    @Severity(SeverityLevel.NORMAL)
    @CsvSource({
        "1, george.bluth@reqres.in, George, Bluth",
        "2, janet.weaver@reqres.in, Janet, Weaver",
        "3, emma.wong@reqres.in, Emma, Wong"
    })
    void getSingleUserWithSpecificDataTest(int userId, String expectedEmail, 
                                          String expectedFirstName, String expectedLastName) {
        UserResponse response = step("Получение пользователя с ID: " + userId, () -> 
            userApi.getSingleUser(userId)
        );

        step("Проверка конкретных данных пользователя " + expectedFirstName, () -> {
            UserData user = response.getData();
            assertThat(user.getId()).isEqualTo(userId);
            assertThat(user.getEmail()).isEqualTo(expectedEmail);
            assertThat(user.getFirstName()).isEqualTo(expectedFirstName);
            assertThat(user.getLastName()).isEqualTo(expectedLastName);
            assertThat(user.getAvatar())
                .startsWith("https://reqres.in/img/faces/")
                .endsWith("-image.jpg");
        });
    }

    @Test
    @DisplayName("Получение несуществующего пользователя")
    @Severity(SeverityLevel.CRITICAL) 
    @Tag("negative")
    void getUserNotFoundTest() {
        step("Попытка получить несуществующего пользователя с ID 23", () -> {
            userApi.getUserNotFound(23);
        });
    }

    @Test
    @DisplayName("Создание пользователя с полной проверкой ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("smoke")
    void createUserWithFullValidationTest() {
        CreateUserRequest userData = step("Подготовка данных пользователя", () -> 
            validUserData()
        );

        CreateUserResponse response = step("Создание пользователя", () -> 
            userApi.createUser(userData)
        );

        step("Полная проверка созданного пользователя", () -> {
            assertThat(response.getName()).isEqualTo("John Doe");
            assertThat(response.getJob()).isEqualTo("QA Engineer");
            assertThat(response.getId()).isNotNull().isNotEmpty();
            assertThat(response.getCreatedAt()).isNotNull().isNotEmpty();
        });
    }

    @Test
    @DisplayName("Обновление данных пользователя")
    @Severity(SeverityLevel.NORMAL)
    @Tag("crud")
    void updateUserTest() {
        CreateUserRequest updateData = step("Подготовка данных для обновления", () ->
            CreateUserRequest.builder()
                .name("Updated Name")
                .job("Updated Job")
                .build()
        );

        CreateUserResponse response = step("Обновление пользователя с ID 2", () -> 
            userApi.updateUser(2, updateData)
        );

        step("Проверка обновленных данных", () -> {
            assertThat(response.getName()).isEqualTo("Updated Name");
            assertThat(response.getJob()).isEqualTo("Updated Job");
            assertThat(response.getUpdatedAt() != null || response.getCreatedAt() != null).isTrue();
        });
    }

    @Test
    @DisplayName("Удаление пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("crud")
    void deleteUserTest() {
        step("Удаление пользователя с ID 2", () -> {
            userApi.deleteUser(2);
        });
    }
}