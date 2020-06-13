package ad.society.apartmentmanagement.transformer;

import ad.society.apartmentmanagement.entity.Apartment;
import ad.society.apartmentmanagement.modal.ApartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ApartmentTransformer implements ObjectTransformer<Apartment, ApartmentVO>{

    @Autowired
    private AddressInfoTransformer addressInfoTransformer;

    @Override
    public ApartmentVO toValueObject(Apartment entity) {
        return ApartmentVO.builder()
                .apartmentId(entity.getApartmentId())
                .apartmentName(entity.getApartmentName())
                .apartmentType(entity.getApartmentType())
                .referenceUserId(entity.getReferenceUserId())
                .addressInfo(addressInfoTransformer.toValueObject(entity.getAddressInfo()))
                .build();
    }

    @Override
    public Apartment toEntity(ApartmentVO dto) {
        return Apartment.builder()
                .apartmentId(dto.getApartmentId())
                .apartmentName(dto.getApartmentName())
                .apartmentType(dto.getApartmentType())
                .referenceUserId(dto.getReferenceUserId())
                .addressInfo(addressInfoTransformer.toEntity(dto.getAddressInfo()))
                .build();
    }
}
