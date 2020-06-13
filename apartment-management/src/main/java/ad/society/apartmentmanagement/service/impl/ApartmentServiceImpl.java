package ad.society.apartmentmanagement.service.impl;

import ad.society.apartmentmanagement.dao.ApartmentDAO;
import ad.society.apartmentmanagement.modal.ApartmentSearchFilterVO;
import ad.society.apartmentmanagement.modal.ApartmentVO;
import ad.society.apartmentmanagement.service.ApartmentService;
import ad.society.apartmentmanagement.transformer.ApartmentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentDAO apartmentDAO;

    @Autowired
    private ApartmentTransformer apartmentTransformer;

    @Override
    public ApartmentVO getApartmentDetails(String apartmentId) {
        return apartmentTransformer.toValueObject(apartmentDAO.getApartmentDetails(apartmentId));
    }

    @Override
    public List<ApartmentVO> getAllApartment() {
        return apartmentDAO.getAllApartment().stream().map(e -> apartmentTransformer.toValueObject(e)).collect(Collectors.toList());
    }

    @Override
    public List<ApartmentVO> searchApartment(ApartmentSearchFilterVO filterVO) {
        return apartmentDAO.searchApartment(filterVO).stream().map(e -> apartmentTransformer.toValueObject(e)).collect(Collectors.toList());
    }

    @Override
    public String createApartment(ApartmentVO apartmentVO) {
        return apartmentDAO.createApartment(apartmentVO);
    }

    @Override
    public String getApartmentName(String apartmentId) {
        return apartmentDAO.getApartmentName(apartmentId);
    }

}
