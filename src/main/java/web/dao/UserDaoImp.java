package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class UserDaoImp implements UserDao{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        return entityManager.createQuery("from User",User.class).getResultList();
    }

    @Override
    public void save(User person) {
        entityManager.persist(person);
    }

    @Override
    public void update(User updatedPerson) {
        entityManager.merge(updatedPerson);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(loadUserById(id));
    }

    @Override
    public User loadUserByUserName(String name) {
        TypedQuery<User> query =  entityManager
                .createQuery("from User user join fetch user.roles where user.firstName=:name",
                        User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public User loadUserById(Long id) {
        return entityManager.find(User.class,id);
    }
}
