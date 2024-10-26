package kounettechnologie.kounet.customerservices.service;



import kounettechnologie.kounet.customerservices.dto.CustomerDTO;
import kounettechnologie.kounet.customerservices.exception.CustomerNotFoundException;
import kounettechnologie.kounet.customerservices.exception.EmailAlreadyExistException;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveNewCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException;
    List<CustomerDTO> getAllCustomers();
    CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> searchCustomers(String keyword);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO)throws CustomerNotFoundException;
    void deleteCustomer(Long id)throws CustomerNotFoundException;
}
