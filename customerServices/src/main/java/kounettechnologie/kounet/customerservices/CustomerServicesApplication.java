package kounettechnologie.kounet.customerservices;

import kounettechnologie.kounet.customerservices.entity.Customer;
import kounettechnologie.kounet.customerservices.repos.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;

@SpringBootApplication
public class CustomerServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServicesApplication.class, args);
    }



    @Bean
    @Profile("!test")
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){

        return args -> {
            List<Customer> customers = List.of(
                    Customer.builder()
                            .firstName("oumar").lastName("kounet").email("kouet@gmail.com").build(),
                    Customer.builder()
                            .firstName("sow").lastName("Yas").email("yas@gmail.com").build(),
                    Customer.builder()
                            .firstName("Harouma").lastName("yamal").email("haroune@gmail.com").build()
            );
            customerRepository.saveAll(customers);
        };
    }

}
