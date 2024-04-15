package CapstoneDesign.Backendserver.repository;

import CapstoneDesign.Backendserver.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext

    EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(String userId) {
        User user = em.find(User.class, userId);//여기서 데이터 없을때 에러처리해야함.
        return user;
    }

    public List<User> findAll() {
        return em.createQuery("select m from User m", User.class).getResultList();
    }

//    public static List<User> findByName(String name) {
//        return em.createQuery("select m from User m where m.name = :name", User.class)
//                .setParameter("name",name).getResultList();
//
//    }
}
