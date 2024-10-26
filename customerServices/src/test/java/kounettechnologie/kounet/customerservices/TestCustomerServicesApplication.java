package kounettechnologie.kounet.customerservices;

import org.springframework.boot.SpringApplication;

public class TestCustomerServicesApplication {

    public static void main(String[] args) {
        SpringApplication.from(CustomerServicesApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
