package edu.hm.REST;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.model.Book;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;


import java.util.ArrayList;
import edu.hm.model.User;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/media/testSet")
public class CreateTestSet {


    /**
     * create ctor to get the logic
     */
    private static MediaAdminAccess mAdm;

    public static void setAccess(MediaAdminAccess mediaAccess) {
        mAdm = mediaAccess;
    }

    @GET
    public static void createTestSet(){
        User user1 = new User("user1", "user1");
        mAdm.createBook("test1", "test1","test", user1);

        User user2 = new User("user2", "user2");
        mAdm.createBook("test2", "test2","test", user2);

    }
}