package ad.society.apartmentmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApartmentException {
    private String errorCode;
    private String description;
    private Throwable error;
}
