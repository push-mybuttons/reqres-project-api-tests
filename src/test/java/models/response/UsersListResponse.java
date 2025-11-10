package models.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.User;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersListResponse {

    private Integer page;

    @SerializedName("per_page")
    private Integer perPage;

    private Integer total;

    @SerializedName("total_pages")
    private Integer totalPages;

    private List<User> data;
}