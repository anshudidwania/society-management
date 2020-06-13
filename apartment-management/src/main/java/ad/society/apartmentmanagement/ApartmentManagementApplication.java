package ad.society.apartmentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApartmentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartmentManagementApplication.class, args);
	}

}
