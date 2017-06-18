package edu.hm.REST;

import com.google.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Maximilian on 28.05.2017.
 */
@Path("/userapi")
public class UserAPI {


    /**
     * create ctor to get the logic
     */
    @Inject
    private static UserAdminAccess uAdm;
    private static final int STATUS200 = 200;
    private static final int STATUS400 = 400;

    /**
     * activates a User.
     * @param dataMsg the new users data
     * @return a response
     */
    @POST
    @Consumes("application/json")
    public Response activateUser(final String dataMsg) {
        JSONObject obj = new JSONObject(dataMsg);
        String user = null;
        String token = null;
        String result = null;
        try {
            user = obj.getString("user");
            token = obj.getString("token");

        } catch (JSONException e) {
        }

        if (uAdm.activateUser(user, token)) {
            result = "user activated";
        } else {
            result = "insuficient permission";
        }
        return Response
                .status(STATUS200)
                .entity(result)
                .build();

    }


    /**
     * sets the UserAcces interface this class uses.
     * @param userAccess the interface impelmentation to be used
     */
    public static void setAccess(UserAdminAccess userAccess) {
        uAdm = userAccess;
    }

}
