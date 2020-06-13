package ad.society.usermanagement.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(collection = "UserDetails")
public class User {
    private String userId;
    private String userName;
    private String password;
    private String Uuid;
    private String userType;
    private String referenceUserId;
    private String societyId;
    private List<String> roles;
    private List<AddressInfo> addressInfo;

    @Builder
    @Data
    public static class AddressInfo {
        private String addressLine1;
        private String addressLine2;
        private String landmark;
        private String city;
        private String district;
        private String state;
        private String country;
        private String zipcode;

    }
}
