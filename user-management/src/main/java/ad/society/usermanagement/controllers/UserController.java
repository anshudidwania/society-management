package ad.society.usermanagement.controllers;

import ad.society.usermanagement.exception.UserException;
import ad.society.usermanagement.modal.ResponseVO;
import ad.society.usermanagement.modal.UserSearchFilterVO;
import ad.society.usermanagement.modal.UserVO;
import ad.society.usermanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path = "/createUser")
    public ResponseVO createUser(@RequestBody UserVO userVO){
        try{
            String userId = userService.createUser(userVO);
            logger.debug("createUser successfully", userId);
            return buildResponse(userId, null);
        }catch (Exception ex){
            logger.error("getUserDetails faliure", ex);
            return buildResponse(null, ex);
        }
    }

    @GetMapping(path = "/{user-id}")
    public ResponseVO getUserDetails(@PathVariable ("user-id") String userId) {
        try{
            UserVO vo = userService.getUserDetails(userId);
            logger.debug("getUserDetails successfully", vo);
            return buildResponse(vo, null);
        }catch (Exception ex){
            logger.error("getUserDetails faliure", ex);
            return buildResponse(null, ex);
        }
    }

    @GetMapping(path = "/findAll")
    public ResponseVO getAllUsers(){
        try{
            List<UserVO> ls = userService.getAllUsers();
            logger.debug("getAllUsers successfully", ls);
            return buildResponse(ls, null);
        }catch (Exception ex){
            logger.error("getAllUsers faliure", ex);
            return buildResponse(null, ex);
        }
    }

    @PostMapping(path = "/find")
    public ResponseVO searchUser(@RequestBody UserSearchFilterVO filterVO){
        try{
            List<UserVO> ls = userService.searchUser(filterVO);
            logger.debug("getAllUsers successfully", ls);
            return buildResponse(ls, null);
        }catch (Exception ex){
            logger.error("getAllUsers faliure", ex);
            return buildResponse(null, ex);
        }
    }

    private ResponseVO buildResponse(Object data, Exception error) {
        if (isNull(error)) {
            return ResponseVO.builder()
                    .success(true)
                    .data(data).build();
        }else{
            UserException ex = UserException.builder()
                    .errorCode("E200")
                    .description(error.getMessage()).build();
            return ResponseVO.builder()
                    .success(false)
                    .error(ex).build();
        }
    }

}
