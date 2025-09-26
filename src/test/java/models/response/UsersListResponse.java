package models.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersListResponse {
    
    @SerializedName("page")
    private Integer page;
    
    @SerializedName("per_page")
    private Integer perPage;
    
    @SerializedName("total")
    private Integer total;
    
    @SerializedName("total_pages")
    private Integer totalPages;
    
    @SerializedName("data")
    private List<UserData> data;
}