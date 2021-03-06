package edu.hm;

import edu.hm.Data.UserData;
import edu.hm.Logic.UserAdministartion;
import edu.hm.REST.Login;
import edu.hm.REST.Logout;
import edu.hm.REST.UserAPI;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;
import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;

/**
 * Start the application without an AppServer like tomcat.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class JettyStarter {

    public static final String APP_URL = "/";
    public static final int PORT = 8082;
    public static final String WEBAPP_DIR = "./src/main/webapp/";

    /**
     * Deploy local directories using Jetty without needing a container-based deployment.
     * @param args unused
     * @throws Exception might throw for several reasons.
     */
    public static void main(String... args) throws Exception {

        MediumData mdata = new MediumData();
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        Login.setAccess(userAdministartion);
        Logout.setAccess(userAdministartion);
        UserAPI.setAccess(userAdministartion);
        MediumAdministartion mAdm = new MediumAdministartion(mdata, userAdministartion);
        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        jetty.join();


    }

}
