package ad.society.apartmentmanagement.transformer;

import ad.society.apartmentmanagement.entity.Apartment;
import ad.society.apartmentmanagement.modal.AddressInfoVO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AddressInfoTransformer implements ObjectTransformer<Apartment.AddressInfo, AddressInfoVO>{
    @Override
    public AddressInfoVO toValueObject(Apartment.AddressInfo entity) {
        if(!Objects.isNull(entity)) {
            return AddressInfoVO.builder()
                    .addressLine1(entity.getAddressLine1())
                    .addressLine2(entity.getAddressLine2())
                    .city(entity.getCity())
                    .district(entity.getDistrict())
                    .country(entity.getCountry())
                    .landmark(entity.getLandmark())
                    .state(entity.getState())
                    .zipcode(entity.getZipcode())
                    .build();
        }
        return null;
    }

    @Override
    public Apartment.AddressInfo toEntity(AddressInfoVO dto) {
        if (!Objects.isNull(dto)) {
            return Apartment.AddressInfo.builder()
                    .addressLine1(dto.getAddressLine1())
                    .addressLine2(dto.getAddressLine2())
                    .city(dto.getCity())
                    .district(dto.getDistrict())
                    .country(dto.getCountry())
                    .landmark(dto.getLandmark())
                    .state(dto.getState())
                    .zipcode(dto.getZipcode())
                    .build();
        }
        return null;
    }
}
