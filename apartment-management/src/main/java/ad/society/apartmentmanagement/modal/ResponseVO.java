package ad.society.apartmentmanagement.modal;

import ad.society.apartmentmanagement.exception.ApartmentException;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseVO {
    private boolean success=true;
    private ApartmentException error;
    private Object data;
}
