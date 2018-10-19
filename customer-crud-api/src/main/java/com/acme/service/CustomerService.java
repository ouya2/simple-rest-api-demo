package com.acme.service;

import com.acme.model.Customer;
import com.acme.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer createCustomer(Customer customer) {
    log.info("Saving new customer ...");
    return customerRepository.save(customer);
  }

  public Customer updateCustomer(Customer customerToUpdate) {
    log.info("Update the customer with id - " + customerToUpdate.getId());
    return customerRepository.save(customerToUpdate);
  }

  public void deleteCustomer(Long id) {
    log.info("Deleting customer for id - " + id);
    customerRepository.delete(id);
  }

  public Customer getCustomer(Long id) {
    log.info("Getting customer..");
    return customerRepository.findOne(id);
  }
}
