package ad.society.usermanagement.resource.apartment;

import ad.society.usermanagement.configuration.SocietyProperties;
import ad.society.usermanagement.modal.ResponseVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@Service
public class ApartmentResource {

    //private WebClient webClient;

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    SocietyProperties properties;


    @HystrixCommand(fallbackMethod = "getSocietyNameFallBack")
    public String getSocietyName (String societyId){
        WebClient webClient = webClientBuilder
                .filter(basicAuthentication(properties.getUserName(), properties.getPassword()))
                .baseUrl(properties.getBackendUri())
                .build();
        ResponseVO resVO = webClient
                .get()
                .uri("/name/{societyId}", societyId)
                .retrieve()
                .bodyToMono(ResponseVO.class)
                .block();
        return (String)resVO.getData();
    }

    public String getSocietyNameFallBack (String societyId){
        return null;
    }
}
