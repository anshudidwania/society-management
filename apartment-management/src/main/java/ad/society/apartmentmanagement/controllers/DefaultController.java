package ad.society.apartmentmanagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping(path = "/heartBeat")
    public String heartBeat() {
        return "Apartment registration microservice is up and running!!";
    }
}
