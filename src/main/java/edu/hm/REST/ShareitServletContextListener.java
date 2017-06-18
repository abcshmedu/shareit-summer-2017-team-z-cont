package edu.hm.REST;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

import edu.hm.Data.MediumData;
import edu.hm.Data.UserData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.Logic.MediumDataAccess;
import edu.hm.Logic.UserAdministartion;
import edu.hm.Logic.UserDataAccess;

/**
 * Context Listener to enable usage of google guice together with jersey.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class ShareitServletContextListener extends GuiceServletContextListener {

    private static final Injector INJECTOR = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(MediumDataAccess.class).to(MediumData.class);
            bind(UserDataAccess.class).to(UserData.class);
            bind(MediaAdminAccess.class).to(MediumAdministartion.class);
            bind(UserAdminAccess.class).to(UserAdministartion.class);
        }
    });

    @Override
    protected Injector getInjector() {
        return INJECTOR;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the Application class.
     * @return Injector instance.
     */
    static Injector getInjectorInstance() {
        return INJECTOR;
    }

}
