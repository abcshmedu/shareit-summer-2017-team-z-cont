package edu.hm.REST;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import edu.hm.Data.MediumData;
import edu.hm.Data.UserData;
import edu.hm.model.User;
import edu.hm.model.Medium;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/media/books")
public class media_books {

    // List all available Books.
    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        MediumData mdata = new MediumData();
        User user = new User("test", "test");
        // add medium just for some output
        mdata.addMedium(user, "bla", "test", "muc");
        return mdata.toString();
    }
}