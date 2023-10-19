package web.dao;

import web.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(long id, User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(long id) {
        User user = showUser(id);

        if (null == user) {
            throw new NullPointerException("User not found");
        }

        entityManager.remove(user);
        entityManager.flush();
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User showUser(long id) {
        return entityManager.find(User.class, id);
    }



}
