package tests;

import api.AuthenticationApi;
import config.BaseApiTest;
import io.qameta.allure.*;
import models.request.LoginRequest;
import models.response.ErrorResponse;
import models.response.LoginResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static helpers.TestData.Login.*;

@Epic("Reqres API")
@Feature("Authentication")
@Story("User Login")
@Owner("MariiaP")
public class AuthenticationTest extends BaseApiTest {
    
    private final AuthenticationApi authApi = new AuthenticationApi();
    
    @Test
    @DisplayName("Успешная авторизация пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("smoke")
    void successfulLoginTest() {
        LoginRequest loginData = step("Подготовка валидных данных", () -> validLoginData());

        LoginResponse response = step("Выполнение авторизации", () -> 
            authApi.login(loginData)
        );

        step("Проверка токена", () -> {
            assertThat(response.getToken()).isNotNull().isNotEmpty();
        });
    }
    
    @ParameterizedTest(name = "Неуспешная авторизация: {2}")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("invalidLoginDataProvider")
    void unsuccessfulLoginTest(LoginRequest loginData, String expectedError, String testCaseName) {
        
        ErrorResponse response = step("Выполнение неуспешной авторизации", () -> 
            authApi.loginWithInvalidData(loginData)
        );
        
        step("Проверка ошибки", () -> {
            assertThat(response.getError()).isEqualTo(expectedError);
        });
    }

    @Test
    @DisplayName("Авторизация с несуществующим пользователем")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    void nonExistentUserLoginTest() {
        LoginRequest loginData = step("Подготовка данных несуществующего пользователя", () ->
            LoginRequest.builder()
                .email("nonexistent@test.com")
                .password("wrongpassword")
                .build()
        );
        
        ErrorResponse response = step("Выполнение авторизации", () -> 
            authApi.loginWithNonExistentUser(loginData)
        );
        
        step("Проверка ошибки", () -> {
            assertThat(response.getError()).isNotEmpty();
        });
    }

    static Stream<Arguments> invalidLoginDataProvider() {
        return Stream.of(
            Arguments.of(emptyEmailData(), "Missing email or username", "Авторизация с пустым email"),
            Arguments.of(emptyPasswordData(), "Missing password", "Авторизация с пустым паролем"), 
            Arguments.of(LoginRequest.builder().build(), "Missing email or username", "Авторизация с полностью пустыми данными")
        );
    }
}
