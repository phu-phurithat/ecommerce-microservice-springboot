package com.phu.customer_service.customer;

import com.phu.customer_service.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.findById(request.getId())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Customer with id %s not found", request.getId())
                ));
        mergerCustomer(customer, request);

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.getFirstName())) {
            customer.setFirstName(request.getFirstName());
        }
        if(StringUtils.isNotBlank(request.getLastName())) {
            customer.setLastName(request.getLastName());
        }
        if(StringUtils.isNotBlank(request.getEmail())) {
            customer.setEmail(request.getEmail());
        }
        if(request.getAddress() != null) {
            customer.setAddress(request.getAddress());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    public boolean existById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(() ->new CustomerNotFoundException(format("No customer found with provided ID :: %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            return;
        } else {
            throw new CustomerNotFoundException(format("No customer found with provided ID :: %s", customerId));
        }
    }
}
