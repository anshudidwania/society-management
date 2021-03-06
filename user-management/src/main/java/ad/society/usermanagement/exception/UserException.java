package ad.society.usermanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserException {
    private String errorCode;
    private String description;
}
