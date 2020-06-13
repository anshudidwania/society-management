package ad.society.apartmentmanagement.modal;

import ad.society.apartmentmanagement.entity.Apartment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ApartmentVO {
    private String apartmentId;
    private String apartmentName;
    private String apartmentType;
    private String referenceUserId;
    private AddressInfoVO addressInfo;

}
