package ad.society.usermanagement.service;

import ad.society.usermanagement.modal.UserSearchFilterVO;
import ad.society.usermanagement.modal.UserVO;

import java.util.List;


public interface UserService {

    UserVO getUserDetails(String userId);
    List<UserVO> getAllUsers();
    List<UserVO> searchUser(UserSearchFilterVO filterVO);

    String createUser(UserVO userVO);
}
