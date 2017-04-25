package edu.hm;

import edu.hm.REST.TestRess;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import edu.hm.REST.media_books;

/**
 * Start the application without an AppServer like tomcat.
 * @author <a mailto:axel.boettcher@hm.edu>Axel B&ouml;ttcher</a>
 *
 */
public class Bla {

    public static final String APP_URL = "/";
    public static final int PORT = 8082;
    public static final String WEBAPP_DIR = "./src/main/webapp/";

    /**
     * Deploy local directories using Jetty without needing a container-based deployment.
     * @param args unused
     * @throws Exception might throw for several reasons.
     */
    public static void main(String... args) throws Exception {

        TestRess test = new TestRess();
        System.out.println(test.getClichedMessage());

        media_books media = new media_books();
        System.out.println(media.getClichedMessage());
    }

}
