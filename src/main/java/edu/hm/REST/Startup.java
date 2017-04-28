package edu.hm.REST;

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
        Media_books.setAccess(mediumadmin);
        Media_disks.setAccess(mediumadmin);

    }
}