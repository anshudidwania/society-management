package ad.society.usermanagement.service.impl;

import ad.society.usermanagement.dao.UserDAO;
import ad.society.usermanagement.modal.ResponseVO;
import ad.society.usermanagement.modal.UserSearchFilterVO;
import ad.society.usermanagement.modal.UserVO;
import ad.society.usermanagement.service.UserService;
import ad.society.usermanagement.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserTransformer userTransformer;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserVO getUserDetails(String userId) {
        UserVO vo = userTransformer.toValueObject(userDAO.getUserDetails(userId));
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("P1_MS_USER", "117bf42a6662e0da17ad73c7e6");
        HttpEntity request = new HttpEntity(headers);
        //ResponseEntity<ResponseVO> response = restTemplate.exchange("http://localhost:8002/society/name/" + vo.getSocietyId(), HttpMethod.GET, request, ResponseVO.class);
        ResponseEntity<ResponseVO> response = restTemplate.exchange("http://apartment-management/society/name/" + vo.getSocietyId(), HttpMethod.GET, request, ResponseVO.class);
        ResponseVO resVO = response.getBody();
        //ResponseVO resVO = restTemplate.getForObject("http://apartment-management/society/name/" + vo.getSocietyId(), ResponseVO.class);
        vo.setSocietyName((String)resVO.getData());
        return vo;
    }

    @Override
    public List<UserVO> getAllUsers() {
        return userDAO.getAllUsers().stream().map(e -> userTransformer.toValueObject(e)).collect(Collectors.toList());
    }

    @Override
    public List<UserVO> searchUser(UserSearchFilterVO filterVO) {
        return userDAO.searchUser(filterVO).stream().map(e -> userTransformer.toValueObject(e)).collect(Collectors.toList());
    }

    @Override
    public String createUser(UserVO userVO) {
        return userDAO.createUser(userVO);
    }

}
