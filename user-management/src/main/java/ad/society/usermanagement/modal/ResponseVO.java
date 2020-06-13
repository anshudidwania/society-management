package ad.society.usermanagement.modal;

import ad.society.usermanagement.exception.UserException;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseVO {
    private boolean success=true;
    private UserException error;
    private Object data;
}
