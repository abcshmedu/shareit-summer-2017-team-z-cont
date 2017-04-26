package edu.hm;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;
import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.REST.RestInter;

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
        MediumAdministartion mAdm = new MediumAdministartion(mdata);
        RestInter rest = new RestInter(mAdm);


        Server jetty = new Server(PORT);
        jetty.setHandler(new WebAppContext(WEBAPP_DIR, APP_URL));
        jetty.start();
        System.out.println("Jetty listening on port " + PORT);
        jetty.join();


    }

}
