package kounettechnologie.kounet.customerservices.mapper;

import kounettechnologie.kounet.customerservices.dto.CustomerDTO;
import kounettechnologie.kounet.customerservices.entity.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {
    private CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void shouldMapCustomerToCustomerDTO() {
        Customer givenCustomer = Customer.builder()
                .id(1L).firstName("ahamed").lastName("kounet").email("dmed@gmail.com")
                .build();
        CustomerDTO expected = CustomerDTO.builder()
                .id(1L).firstName("ahamed").lastName("kounet").email("dmed@gmail.com")
                .build();

        CustomerDTO result = customerMapper.fromCustomer(givenCustomer);
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }


    @Test
    void shouldMapCustomerDTOtoCustomer() {
        CustomerDTO givenCustomerDTO = CustomerDTO.builder()
                .id(1L).firstName("ahamed").lastName("kounet").email("dmed@gmail.com")
                .build();
        Customer expected = Customer.builder()
                .id(1L).firstName("ahamed").lastName("kounet").email("dmed@gmail.com")
                .build();
        Customer result = customerMapper.fromCustomerDTO(givenCustomerDTO);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }


    @Test
    void shouldMapListOfCustomersToListCustomerDTOs() {
        List<Customer> givenCustomers=List.of(
                Customer.builder().id(1L).firstName("ahamed").lastName("kounet").email("dmed@gmail.com")
                .build() ,
                Customer.builder() .id(2L).firstName("oumar").lastName("diallo").email("oumar@gmail.com")
                        .build()
        );
        List<CustomerDTO> expected=List.of(
                CustomerDTO.builder().id(1L).firstName("ahamed").lastName("kounet").email("dmed@gmail.com")
                        .build() ,
                CustomerDTO.builder().id(2L).firstName("oumar").lastName("diallo").email("oumar@gmail.com")
                        .build()
        );
        List<CustomerDTO> result = customerMapper.fromListCustomers(givenCustomers);
        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldNotMapNullCustomerToCustomerDTO() {
        AssertionsForClassTypes.assertThatThrownBy(
                ()->customerMapper.fromCustomer(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
