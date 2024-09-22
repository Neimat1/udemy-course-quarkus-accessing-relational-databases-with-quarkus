package com.course.quarkus.panache;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndFindAPublisher() {

        Publisher publisher = new Publisher("name");

        Publisher.persist(publisher);
        Assertions.assertNotNull(publisher.id);

        publisher = Publisher.findById(publisher.id);
        Assertions.assertEquals("name", publisher.name);

    }
}
