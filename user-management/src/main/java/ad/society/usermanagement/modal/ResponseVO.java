package ad.society.usermanagement.modal;

import ad.society.usermanagement.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {
    private boolean success=true;
    private UserException error;
    private Object data;
}
