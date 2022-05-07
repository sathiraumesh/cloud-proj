package com.mr.mymap.repositories;

import com.mr.mymap.domain.User;
import com.mr.mymap.entities.ProductEntity;
import com.mr.mymap.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public UserEntity save(UserEntity user) {
        em.persist(user);
        return user;
    }

    public UserEntity update(UserEntity user) {
        em.persist(user);
        return user;
    }

    public Optional<UserEntity> findByEmail(String email) {
        return em.createQuery("select u from UserEntity u " +
                        "where u.email = lower(:email) ", UserEntity.class)
                .setParameter("email", email.toLowerCase())
                .setMaxResults(1)
                .getResultList().stream()
                .findFirst();
    }
}
