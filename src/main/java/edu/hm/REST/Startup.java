package edu.hm.REST;

import edu.hm.Data.UserData;
import edu.hm.Logic.UserAdministartion;
import edu.hm.model.User;

import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 */
public class Startup implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mdata = new MediumData();
        MediumAdministartion mediumadmin = new MediumAdministartion(mdata, userAdministartion);

        User dummy = new User("dummy", "dummy");
        dummy.setActivated(true);

        MediaBooks.setAccess(mediumadmin, dummy);
        MediaDisks.setAccess(mediumadmin, dummy);
        CreateUser.setAccess(userAdministartion);
        Login.setAccess(userAdministartion);
        Logout.setAccess(userAdministartion);
        UserAPI.setAccess(userAdministartion);



    }
}