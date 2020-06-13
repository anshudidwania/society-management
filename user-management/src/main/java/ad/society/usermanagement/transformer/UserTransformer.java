package ad.society.usermanagement.transformer;

import ad.society.usermanagement.entity.User;
import ad.society.usermanagement.modal.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class UserTransformer implements ObjectTransformer<User, UserVO>{

    @Autowired
    private AddressInfoTransformer addressInfoTransformer;

    @Override
    public UserVO toValueObject(User entity) {
        return UserVO.builder()
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .userType(entity.getUserType())
                .Uuid(entity.getUuid())
                .societyId(entity.getSocietyId())
                .referenceUserId(entity.getReferenceUserId())
                .roles(new ArrayList<String>(entity.getRoles()))
                .addressInfo(entity.getAddressInfo().stream().map(e -> addressInfoTransformer.toValueObject(e)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public User toEntity(UserVO dto) {
        return User.builder()
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .userType(dto.getUserType())
                .Uuid(dto.getUuid())
                .societyId(dto.getSocietyId())
                .referenceUserId(dto.getReferenceUserId())
                .roles(new ArrayList<String>(dto.getRoles()))
                .addressInfo(dto.getAddressInfo().stream().map(e -> addressInfoTransformer.toEntity(e)).collect(Collectors.toList()))
                .build();
    }
}
