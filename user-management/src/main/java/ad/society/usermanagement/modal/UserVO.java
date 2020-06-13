package ad.society.usermanagement.modal;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserVO {
    private String userId;
    private String userName;
    private String password;
    private String Uuid;
    private String userType;
    private String referenceUserId;
    private String societyId;
    private List<String> roles;
    private List<AddressInfoVO> addressInfo;

}
