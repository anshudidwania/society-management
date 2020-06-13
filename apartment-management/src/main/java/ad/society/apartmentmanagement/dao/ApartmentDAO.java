package ad.society.apartmentmanagement.dao;

import ad.society.apartmentmanagement.entity.Apartment;
import ad.society.apartmentmanagement.modal.ApartmentSearchFilterVO;
import ad.society.apartmentmanagement.modal.ApartmentVO;

import java.util.List;

public interface ApartmentDAO {

    Apartment getApartmentDetails(String apartmentId);
    List<Apartment> getAllApartment();
    List<Apartment> searchApartment(ApartmentSearchFilterVO filterVO);

    String createApartment(ApartmentVO apartmentVO);

    String getApartmentName(String apartmentId);
}
