package ad.society.apartmentmanagement.dao.impl;

import ad.society.apartmentmanagement.dao.ApartmentDAO;
import ad.society.apartmentmanagement.entity.Apartment;
import ad.society.apartmentmanagement.modal.ApartmentSearchFilterVO;
import ad.society.apartmentmanagement.modal.ApartmentVO;
import ad.society.apartmentmanagement.transformer.ApartmentTransformer;
import org.bson.BsonRegularExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ApartmentDAOImpl implements ApartmentDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ApartmentTransformer apartmentTransformer;

    @Override
    public Apartment getApartmentDetails(String apartmentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("apartmentId").is(apartmentId));
        return mongoTemplate.findOne(query, Apartment.class);
    }

    @Override
    public List<Apartment> getAllApartment() {
        Query query = new Query();
        query.fields()
                .include("apartmentId")
                .include("apartmentName")
                .include("apartmentType");
        return mongoTemplate.find(query, Apartment.class);
    }

    @Override
    public List<Apartment> searchApartment(ApartmentSearchFilterVO filterVO) {
        Query query = new Query();
        if(!Objects.isNull(filterVO.getApartmentName())) query.addCriteria(Criteria.where("apartmentName").regex(new BsonRegularExpression(filterVO.getApartmentName(),"i")));
        if(!Objects.isNull(filterVO.getApartmentType())) query.addCriteria(Criteria.where("apartmentType").is(filterVO.getApartmentType()));
        if(!Objects.isNull(filterVO.getAddressLine1())) query.addCriteria(Criteria.where("addressInfo.addressLine1").regex(new BsonRegularExpression(filterVO.getAddressLine1(),"i")));
        if(!Objects.isNull(filterVO.getAddressLine2())) query.addCriteria(Criteria.where("addressInfo.addressLine2").regex(new BsonRegularExpression(filterVO.getAddressLine2(),"i")));
        if(!Objects.isNull(filterVO.getLandmark())) query.addCriteria(Criteria.where("addressInfo.landmark").regex(new BsonRegularExpression(filterVO.getLandmark(),"i")));
        if(!Objects.isNull(filterVO.getCity())) query.addCriteria(Criteria.where("addressInfo.city").regex(new BsonRegularExpression(filterVO.getCity(),"i")));
        if(!Objects.isNull(filterVO.getDistrict())) query.addCriteria(Criteria.where("addressInfo.district").regex(new BsonRegularExpression(filterVO.getDistrict(),"i")));
        if(!Objects.isNull(filterVO.getState())) query.addCriteria(Criteria.where("addressInfo.state").regex(new BsonRegularExpression(filterVO.getState(),"i")));
        if(!Objects.isNull(filterVO.getCountry())) query.addCriteria(Criteria.where("addressInfo.country").regex(new BsonRegularExpression(filterVO.getCountry(),"i")));
        if(!Objects.isNull(filterVO.getZipcode())) query.addCriteria(Criteria.where("addressInfo.zipcode").is(filterVO.getZipcode()));
        return mongoTemplate.find(query, Apartment.class);
    }

    @Override
    public String createApartment(ApartmentVO apartmentVO) {
        Apartment apartment = apartmentTransformer.toEntity(apartmentVO);
        mongoTemplate.save(apartment);
        return apartment.getApartmentId();
    }

    @Override
    public String getApartmentName(String apartmentId) {
        Query query = new Query();
        query.fields().include("apartmentName");
        query.addCriteria(Criteria.where("apartmentId").is(apartmentId));
        Apartment apartment = mongoTemplate.findOne(query, Apartment.class);
        return apartment.getApartmentName();

    }
}
