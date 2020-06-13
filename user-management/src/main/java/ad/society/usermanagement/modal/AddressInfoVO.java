package ad.society.usermanagement.modal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressInfoVO {
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private String city;
    private String district;
    private String state;
    private String country;
    private String zipcode;
}
