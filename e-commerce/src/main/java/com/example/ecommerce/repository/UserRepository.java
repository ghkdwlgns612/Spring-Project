package com.example.ecommerce.repository;

import com.example.ecommerce.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u",User.class)
                .getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.name=:name",User.class)
                .setParameter("name",name)
                .getResultList();
    }
}
