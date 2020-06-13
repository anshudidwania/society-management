package ad.society.usermanagement.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping(path = "/heartBeat")
    public String heartBeat() {
        return "User registration microservice is up and running!!";
    }
}
