package ad.society.usermanagement.dao;

import ad.society.usermanagement.entity.User;
import ad.society.usermanagement.modal.UserSearchFilterVO;
import ad.society.usermanagement.modal.UserVO;

import java.util.List;

public interface UserDAO {

    User getUserDetails(String userId);
    List<User> getAllUsers();
    List<User> searchUser(UserSearchFilterVO filterVO);

    String createUser(UserVO userVO);
}
