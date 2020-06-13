package ad.society.apartmentmanagement.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(collection = "ApartmentDetails")
public class Apartment {
    private String apartmentId;
    private String apartmentName;
    private String apartmentType;
    private String referenceUserId;
    private AddressInfo addressInfo;

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
