package edu.hm.REST;

import edu.hm.model.User;

import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Startup implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        MediumData mdata = new MediumData();
        MediumAdministartion mediumadmin = new MediumAdministartion(mdata);

        User dummy = new User("dummy", "dummy");
        dummy.setActivated(true);

        Media_books.setAccess(mediumadmin, dummy);
        Media_disks.setAccess(mediumadmin, dummy);

    }
}