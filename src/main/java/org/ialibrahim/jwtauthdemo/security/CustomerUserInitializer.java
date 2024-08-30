package org.ialibrahim.jwtauthdemo.security;

import jakarta.annotation.PostConstruct;
import org.ialibrahim.jwtauthdemo.dao.CustomerEntity;
import org.ialibrahim.jwtauthdemo.dao.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserInitializer {
    @Autowired
    private CustomerRepository customerRepository;

    @PostConstruct
    public void init() {
        CustomerEntity testCustomer = new CustomerEntity();
        testCustomer.setId(123L);
        testCustomer.setName("Ahmet");
        testCustomer.setSurname("Korkmaz");
        testCustomer.setUsername("customer1");
        testCustomer.setPassword("7ea68aac0a4dc2024b0106f95a7ca2cc9c712b6f8cc93ca9eb0e30550b4a92b85843fbff");
        customerRepository.save(testCustomer);
    }
}
