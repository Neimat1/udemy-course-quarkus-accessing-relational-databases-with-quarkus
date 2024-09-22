package com.course.quarkus.jpa;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CustomerRepositoryTest {
    @Inject
    CustomerRepository repository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindACustomer() {
        Customer customer = new Customer("first name", "last name", "email@gmail.com");

        repository.persist(customer);
        Assertions.assertNotNull(customer.getId());

        customer = repository.findById(customer.getId());
        Assertions.assertEquals("first name", customer.getFirstName());
    }

}
