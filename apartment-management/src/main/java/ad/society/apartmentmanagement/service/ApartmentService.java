package ad.society.apartmentmanagement.service;

import ad.society.apartmentmanagement.modal.ApartmentSearchFilterVO;
import ad.society.apartmentmanagement.modal.ApartmentVO;

import java.util.List;


public interface ApartmentService {

    ApartmentVO getApartmentDetails(String apartmentId);
    List<ApartmentVO> getAllApartment();
    List<ApartmentVO> searchApartment(ApartmentSearchFilterVO filterVO);

    String createApartment(ApartmentVO apartmentVO);

    String getApartmentName(String apartmentId);
}
