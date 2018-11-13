package ch.heigvd.gamification.dao;

import ch.heigvd.gamification.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.sql.DataSource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Stateless
public class UsersManager extends GenericDAO<User, Long> implements UsersManagerLocal {
    @Resource(lookup = "jdbc/myressource")
    private DataSource dataSource;

    @Override
    public User findByEmail(String mail) {
        User user = null;
        try {
            user = (User) em.createNamedQuery("User.findByEmail").setParameter("email", mail).getSingleResult();
        } catch (NoResultException e) {
            //TODO: LOG
        }
        return user;
    }

    @Override
    public List<User> findByQuery(String query) {
        List<User> users = new LinkedList<>();
        try {
            users = em.createNamedQuery("User.findByQuery").setParameter("query", query).getResultList();
        } catch (NoResultException ex) {
            //TODO: LOG
        }
        return users;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public User createAndReturnUser(User user){
        em.persist(user);
        em.flush();
        return user;
    }
}
