package com.course.quarkus.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Example JPA entity.
 * <p>
 * To use it, get access to a JPA EntityManager via injection.
 * <p>
 * {@code
 *
 * @Inject EntityManager em;
 * <p>
 * public void doSomething() {
 * MyEntity entity1 = new MyEntity();
 * entity1.field = "field-1";
 * em.persist(entity1);
 * <p>
 * List<MyEntity> entities = em.createQuery("from MyEntity", MyEntity.class).getResultList();
 * }
 * }
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue
    public Long id;

    public String field;
}
