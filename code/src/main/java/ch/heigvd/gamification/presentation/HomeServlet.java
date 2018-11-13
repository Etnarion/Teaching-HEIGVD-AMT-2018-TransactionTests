package ch.heigvd.gamification.presentation;

import ch.heigvd.gamification.business.AppAndUserCreator;
import ch.heigvd.gamification.dao.ApplicationsManagerLocal;
import ch.heigvd.gamification.dao.UsersManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    @EJB
    AppAndUserCreator appAndUserCreator;

    @EJB
    UsersManagerLocal usersManager;

    @EJB
    ApplicationsManagerLocal applicationsManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long usersCountBefore = usersManager.count();
        Long applicationsCountBefore = applicationsManager.count();
        response.getWriter().println("User count before : " + usersCountBefore);
        response.getWriter().println("Application count before : " + applicationsCountBefore);

        try {
            appAndUserCreator.createAppAndUser();
        } catch (Exception e) {
            response.getWriter().println("Error during app and user creation");
        }

        Long usersCountAfter = usersManager.count();
        Long applicationsCountAfter = applicationsManager.count();
        response.getWriter().println("User count after : " + usersCountAfter);
        response.getWriter().println("Application count after : " + applicationsCountAfter);
    }
}
