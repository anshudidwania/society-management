package ad.society.usermanagement.service.impl;

import ad.society.usermanagement.dao.UserDAO;
import ad.society.usermanagement.modal.UserSearchFilterVO;
import ad.society.usermanagement.modal.UserVO;
import ad.society.usermanagement.service.UserService;
import ad.society.usermanagement.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserTransformer userTransformer;

    @Override
    public UserVO getUserDetails(String userId) {
        return userTransformer.toValueObject(userDAO.getUserDetails(userId));
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
