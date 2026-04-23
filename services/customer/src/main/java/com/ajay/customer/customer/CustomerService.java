package com.ajay.customer.customer;

import ch.qos.logback.core.util.StringUtil;
import com.ajay.customer.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;
    public CustomerService(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public String createCustomer( CustomerRequest customerRequest) {
        var customer = customerRepository.save(mapper.toCustomer(customerRequest));
        return customer.getId();
    }


    public void updateCustomer( CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(()->new CustomerNotFoundException("Customer not found whith  id " + customerRequest.id()));

        mergeCustomer(customer,customerRequest);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer,  CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName())) {
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())) {
            customer.setLastName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())) {
            customer.setEmail(customerRequest.email());
        }
        if(customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {

        return customerRepository.findAll().stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(String cuId) {
        return customerRepository.findById(cuId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No customer with this id " + cuId));
    }

    public void deleteById(String cuId) {
        if(customerRepository.findById(cuId).isPresent()) {
            customerRepository.deleteById(cuId);
        }else{
            throw new CustomerNotFoundException("No customer with this id " + cuId);
        }
    }
}
