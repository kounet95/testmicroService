package kounettechnologie.kounet.customerservices.service;


import kounettechnologie.kounet.customerservices.dto.CustomerDTO;
import kounettechnologie.kounet.customerservices.entity.Customer;
import kounettechnologie.kounet.customerservices.exception.CustomerNotFoundException;
import kounettechnologie.kounet.customerservices.exception.EmailAlreadyExistException;
import kounettechnologie.kounet.customerservices.mapper.CustomerMapper;
import kounettechnologie.kounet.customerservices.repos.CustomerRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomerServiceImpl underTest;
    @Test
    void shouldSaveNewCustomer() {
        CustomerDTO customerDTO= CustomerDTO.builder()
                .firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer customer= Customer.builder()
                .firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer savedCustomer= Customer.builder()
                .id(1L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        CustomerDTO expected= CustomerDTO.builder()
                .id(1L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.empty());
        Mockito.when(customerMapper.fromCustomerDTO(customerDTO)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(savedCustomer);
        Mockito.when(customerMapper.fromCustomer(savedCustomer)).thenReturn(expected);
        CustomerDTO result = underTest.saveNewCustomer(customerDTO);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldNotSaveNewCustomerWhenEmailExist() {
        CustomerDTO customerDTO= CustomerDTO.builder()
                .firstName("Ismail").lastName("Matar").email("xxxxx@gmail.com").build();
        Customer customer= Customer.builder()
                .id(5L).firstName("Ismail").lastName("Matar").email("xxxxx@gmail.com").build();
        Mockito.when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.of(customer));
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.saveNewCustomer(customerDTO))
                        .isInstanceOf(EmailAlreadyExistException.class);
    }

    @Test
    void shouldGetAllCustomers() {
        List<Customer> customers = List.of(
                Customer.builder().id(1L).firstName("ahamed").lastName("kounet").email("dmed@gmail.com")
                        .build() ,
                Customer.builder() .id(2L).firstName("oumar").lastName("diallo").email("oumar@gmail.com")
                        .build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                CustomerDTO.builder().firstName("Ahmed").lastName("Yassine").email("ahmed@gmail.com").build()
        );
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerMapper.fromListCustomers(customers)).thenReturn(expected);
        List<CustomerDTO> result = underTest.getAllCustomers();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldFindCustomerById() {
        Long customerId = 1L;
        Customer customer=Customer.builder().id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build();
        CustomerDTO expected=CustomerDTO.builder().id(1L).firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.fromCustomer(customer)).thenReturn(expected);
        CustomerDTO result = underTest.findCustomerById(customerId);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    void shouldNotFindCustomerById() {
        Long customerId = 8L;
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.findCustomerById(customerId))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage(null);
    }

    @Test
    void shouldSearchCustomers() {
        String keyword="m";
        List<Customer> customers = List.of(
                Customer.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                Customer.builder().firstName("Ahmed").lastName("Yassine").email("ahmed@gmail.com").build()
        );
        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder().firstName("Mohamed").lastName("Youssfi").email("med@gmail.com").build(),
                CustomerDTO.builder().firstName("Ahmed").lastName("Yassine").email("ahmed@gmail.com").build()
        );
        Mockito.when(customerRepository.findByFirstNameContainingIgnoreCase(keyword)).thenReturn(customers);
        Mockito.when(customerMapper.fromListCustomers(customers)).thenReturn(expected);
        List<CustomerDTO> result = underTest.searchCustomers(keyword);
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void updateCustomer() {
        Long customerId= 6L;
        CustomerDTO customerDTO= CustomerDTO.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer customer= Customer.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Customer updatedCustomer= Customer.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        CustomerDTO expected= CustomerDTO.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        Mockito.when(customerMapper.fromCustomerDTO(customerDTO)).thenReturn(customer);
        Mockito.when(customerRepository.save(customer)).thenReturn(updatedCustomer);
        Mockito.when(customerMapper.fromCustomer(updatedCustomer)).thenReturn(expected);
        CustomerDTO result = underTest.updateCustomer(customerId,customerDTO);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    void shouldDeleteCustomer() {
        Long customerId =1L;
        Customer customer= Customer.builder()
                .id(6L).firstName("Ismail").lastName("Matar").email("ismail@gmail.com").build();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        underTest.deleteCustomer(customerId);
        Mockito.verify(customerRepository).deleteById(customerId);
    }
    @Test
    void shouldNotDeleteCustomerIfNotExist() {
        Long customerId =9L;
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.deleteCustomer(customerId))
                        .isInstanceOf(CustomerNotFoundException.class);
    }
}
