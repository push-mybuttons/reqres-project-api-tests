package models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private User data;
}