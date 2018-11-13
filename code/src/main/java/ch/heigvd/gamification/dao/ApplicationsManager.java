package ch.heigvd.gamification.dao;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ApplicationsManager extends GenericDAO<Application, Long> implements ApplicationsManagerLocal {
    @Override
    public List<Application> findByUser(User user) {
        List<Application> applications = new LinkedList<>();
        applications = em.createNamedQuery("Application.findByUser")
                .setParameter("user", user.getId())
                .getResultList();
        return  applications;
    }

    @Override
    public List<Application> findByUserByPage(User user, int pageSize, int pageIndex) {
        List<Application> applications = new LinkedList<>();
        applications = em.createNamedQuery("Application.findByUser")
                .setParameter("user", user.getId())
                .setMaxResults(pageSize)
                .setFirstResult(pageIndex*pageSize)
                .getResultList();
        return applications;
    }

    @Override
    public List<Application> findByQuery(User user, String query) {
        List<Application> applications = new LinkedList<>();
        try {
            applications = em.createNamedQuery("Application.findByQuery")
                    .setParameter("user", user.getId())
                    .setParameter("query", query)
                    .getResultList();
        } catch (NoResultException ex) {
            //TODO: LOG
        }
        return applications;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public Application createAndReturnApplication(Application application) {
        em.persist(application);
        em.flush();
        return application;
    }
}
