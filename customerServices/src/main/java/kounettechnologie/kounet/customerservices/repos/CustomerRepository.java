package kounettechnologie.kounet.customerservices.repos;


import kounettechnologie.kounet.customerservices.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByFirstNameContainingIgnoreCase(String keyword);
    Optional<Customer> findByEmail(String email);
}
