package helpers;

import models.request.LoginRequest;
import models.request.CreateUserRequest;

public class TestData {

    public static class Login {
        public static final String VALID_EMAIL = "eve.holt@reqres.in";
        public static final String VALID_PASSWORD = "cityslicka";

        public static LoginRequest validLoginData() {
            return LoginRequest.builder()
                    .email(VALID_EMAIL)
                    .password(VALID_PASSWORD)
                    .build();
        }

        public static LoginRequest emptyEmailData() {
            return LoginRequest.builder()
                    .email("")
                    .password(VALID_PASSWORD)
                    .build();
        }

        public static LoginRequest emptyPasswordData() {
            return LoginRequest.builder()
                    .email(VALID_EMAIL)
                    .password("")
                    .build();
        }
    }

    public static class Users {
        public static CreateUserRequest validUserData() {
            return CreateUserRequest.builder()
                    .name("John Doe")
                    .job("QA Engineer")
                    .build();
        }

    }
}
