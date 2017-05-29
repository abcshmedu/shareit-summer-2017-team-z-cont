package edu.hm.REST;

import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Maximilian on 28.05.2017.
 */
@Path("/logout")
public class Logout {


    /**
     * create ctor to get the logic
     */
    private static UserAdminAccess uAdm;
    private static final int STATUS200 = 200;
    private static final int STATUS400 = 400;


    /**
     * logs a User out.
     *
     * @param dataMsg the new Discs data
     * @return a response
     */
    @POST
    @Consumes("application/json")
    public Response logout(final String dataMsg) {

        JSONObject obj = new JSONObject(dataMsg);
        String user;
        String token = null;

        try {
            user = obj.getString("user");
            token = obj.getString("token");

        } catch (JSONException e) {
            return Response.status(STATUS400)
                    .entity("wrong credentials")
                    .build();
        }

        uAdm.logOut(token);
        String result = "loggedout";
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