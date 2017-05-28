package edu.hm.REST;

import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Maximilian on 28.05.2017.
 */
public class UserAPI {


    /**
     * create ctor to get the logic
     */
    private static UserAdminAccess uAdm;
    private static final int STATUS200 = 200;
    private static final int STATUS400 = 400;


    /**
     * logs a User in.
     * @param dataMsg the new Discs data
     * @return a response
     */
    @POST
    @Consumes("application/json")
    public Response login(final String dataMsg) {

        JSONObject obj = new JSONObject(dataMsg);
        String user;
        String password;

        try {
            user = obj.getString("user");
            password = obj.getString("password");

        } catch (JSONException e) {
            return Response.status(STATUS400)
                    .entity("wrong credentials")
                    .build();
        }


        String result = uAdm.logIn(user, password);
        return Response
                .status(STATUS200)
                .entity(result)
                .build();
    }

    /**
     * logs a User out.
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
     * creates a new User.
     * @param dataMsg the new users data
     * @return a response
     */
    @POST
    @Consumes("application/json")
    public Response createUser(final String dataMsg) {
        JSONObject obj = new JSONObject(dataMsg);
        String user = null;
        String password = null;

        try {
            user = obj.getString("user");
            password = obj.getString("password");

        } catch (JSONException e) {
        }


        String result = uAdm.createUser(user, password).toString();
        return Response
                .status(STATUS200)
                .entity(result)
                .build();

    }

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
