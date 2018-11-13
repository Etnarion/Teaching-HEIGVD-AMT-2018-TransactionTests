package ch.heigvd.gamification.business;

import ch.heigvd.gamification.dao.ApplicationsManagerLocal;
import ch.heigvd.gamification.dao.UsersManagerLocal;
import ch.heigvd.gamification.model.Application;
import ch.heigvd.gamification.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.UUID;

@Stateless
public class AppAndUserCreator {

    @EJB
    UsersManagerLocal usersManager;

    @EJB
    ApplicationsManagerLocal applicationsManager;

    public void createAppAndUser() {
        User newUser = new User(
                "Alain",
                "Dutoit",
                UUID.randomUUID().toString(),
                "1234",
                true,
                false,
                false);
        usersManager.createAndReturnUser(newUser);
        User appUser = usersManager.findByEmail("l.vanthillo@gmail.com");
        Application linkedApp = new Application(
                UUID.randomUUID().toString(),
                "You can browse moving images in a multi colored theme",
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                appUser);
        applicationsManager.createAndReturnApplication(linkedApp);
        throw new RuntimeException();
    }
}
