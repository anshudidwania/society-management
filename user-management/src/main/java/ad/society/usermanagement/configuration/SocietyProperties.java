package ad.society.usermanagement.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "society")
@Data
public class SocietyProperties {
    private String backendUri;
    private List<String> headers;
    private String userName;
    private String password;

}
