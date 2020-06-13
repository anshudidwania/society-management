package ad.society.usermanagement.transformer;

import ad.society.usermanagement.entity.User;
import ad.society.usermanagement.modal.AddressInfoVO;
import org.springframework.stereotype.Component;

@Component
public class AddressInfoTransformer implements ObjectTransformer<User.AddressInfo, AddressInfoVO>{
    @Override
    public AddressInfoVO toValueObject(User.AddressInfo entity) {
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

    @Override
    public User.AddressInfo toEntity(AddressInfoVO dto) {
        return User.AddressInfo.builder()
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
}
