package ch.heigvd.gamification.dao;

import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ApplicationsManagerLocal extends IGenericDAO<Application, Long> {
    List<Application> findByUser(User user);
    List<Application> findByUserByPage(User user, int pageSize, int pageIndex);
    List<Application> findByQuery(User user, String query);
    Application createAndReturnApplication(Application application);
}
