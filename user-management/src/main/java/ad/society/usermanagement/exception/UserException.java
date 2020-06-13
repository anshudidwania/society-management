package ad.society.usermanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public class UserException {
    private String errorCode;
    private String description;
}
