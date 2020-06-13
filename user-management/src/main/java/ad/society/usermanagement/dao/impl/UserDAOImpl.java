package ad.society.usermanagement.dao.impl;

import ad.society.usermanagement.dao.UserDAO;
import ad.society.usermanagement.entity.User;
import ad.society.usermanagement.modal.UserSearchFilterVO;
import ad.society.usermanagement.modal.UserVO;
import ad.society.usermanagement.transformer.UserTransformer;
import org.bson.BsonRegularExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public User getUserDetails(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public List<User> searchUser(UserSearchFilterVO filterVO) {
        Query query = new Query();
        if(!Objects.isNull(filterVO.getUserType())) query.addCriteria(Criteria.where("userType").is(filterVO.getUserType()));
        if(!Objects.isNull(filterVO.getSocietyId())) query.addCriteria(Criteria.where("societyId").is(filterVO.getSocietyId()));
        if(!Objects.isNull(filterVO.getAddressLine1())) query.addCriteria(Criteria.where("addressInfo.addressLine1").regex(new BsonRegularExpression(filterVO.getAddressLine1(),"i")));
        if(!Objects.isNull(filterVO.getAddressLine2())) query.addCriteria(Criteria.where("addressInfo.addressLine2").regex(new BsonRegularExpression(filterVO.getAddressLine2(),"i")));
        if(!Objects.isNull(filterVO.getLandmark())) query.addCriteria(Criteria.where("addressInfo.landmark").regex(new BsonRegularExpression(filterVO.getLandmark(),"i")));
        if(!Objects.isNull(filterVO.getCity())) query.addCriteria(Criteria.where("addressInfo.city").regex(new BsonRegularExpression(filterVO.getCity(),"i")));
        if(!Objects.isNull(filterVO.getDistrict())) query.addCriteria(Criteria.where("addressInfo.district").regex(new BsonRegularExpression(filterVO.getDistrict(),"i")));
        if(!Objects.isNull(filterVO.getState())) query.addCriteria(Criteria.where("addressInfo.state").regex(new BsonRegularExpression(filterVO.getState(),"i")));
        if(!Objects.isNull(filterVO.getCountry())) query.addCriteria(Criteria.where("addressInfo.country").regex(new BsonRegularExpression(filterVO.getCountry(),"i")));
        if(!Objects.isNull(filterVO.getZipcode())) query.addCriteria(Criteria.where("addressInfo.zipcode").is(filterVO.getZipcode()));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public String createUser(UserVO userVO) {
        User user = userTransformer.toEntity(userVO);
        mongoTemplate.save(user);
        return user.getUserId();
    }
}
