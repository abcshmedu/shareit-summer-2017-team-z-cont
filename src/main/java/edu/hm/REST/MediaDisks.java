package edu.hm.REST;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import edu.hm.model.Disc;
import edu.hm.model.User;

import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;

/** The Java class will be hosted at the URI path "/helloworld".
 *
 */
@Path("/media/discs")
public class MediaDisks {


    /**
     * create ctor to get the logic
     */
    private static MediaAdminAccess mAdm;
    private static User dummy;
    private static final int STATUS200 = 200;
    private static final int STATUS400 = 400;


    /**
     * list all books.
     * @return List of all books.
     */
    @GET
    @Produces("application/json")
    public Response getAllDiscs() {
        ArrayList<Disc> retlist = new ArrayList<>();
        retlist = mAdm.getAllDiscs();
        if (retlist != null) {
            return Response
                    .status(STATUS200)
                    .entity(retlist.toString())
                    .build();
        } else {
            return Response
                    .status(STATUS400)
                    .entity("no disc found")
                    .build();
        }
    }

    /**
     * list all books with isbn.
     * @param barcode barcode
     * @return list of books with isbn
     */
    @GET
    @Path("{p}")
    public Response getDiscByBarcode(@PathParam("p") String barcode) {
        Disc result;
        result = mAdm.findMediumByBarcode(barcode);
        if (result != null) {
            return Response
                    .status(STATUS200)
                    .entity(result.toString())
                    .build();
        } else {
            return Response
                    .status(STATUS400)
                    .entity("no disc found")
                    .build();
        }

    }

    /**
     * creates a new Disc.
     * @param dataMsg the new Discs data
     * @return a response
     */
    @POST
    @Consumes("application/json")
    public Response createDisc(final String dataMsg) {

        JSONObject obj = new JSONObject(dataMsg);
        String barcode;
        String titel;
        String description;
        String director = null;
        int fsk = 0;
        String user;
        String password;
        User user1 = null;
        String token = null;

        try {
            barcode = obj.getString("barcode");
            titel = obj.getString("title");
            description = obj.getString("description");
            director = obj.getString("director");
            fsk = obj.getInt("fsk");
            user = obj.getString("user");
            password = obj.getString("password");
            token = obj.getString("token");

        } catch (JSONException e) {
            return Response.status(STATUS400)
                    .entity("your json is invalid")
                    .build();
        }


        String result = mAdm.createDisc(barcode, titel, director, fsk, description, token);
        return Response
                .status(STATUS200)
                .entity(result)
                .build();
    }

    /**
     * upadtes a discs data.
     * @param dataMsg the new data
     * @return a response
     */
    @PUT
    @Consumes("application/json")
    public Response updateDisc(final String dataMsg) {
        JSONObject obj = new JSONObject(dataMsg);

        String barcode = null;
        String titel = null;
        String description = null;
        String user = null;
        String password = null;
        User user1 = null;
        String director = null;
        String token = null;
        int fsk = 0;


        try {
            barcode = obj.getString("barcode");
            titel = obj.getString("title");
            description = obj.getString("description");
            director = obj.getString("director");
            fsk = obj.getInt("fsk");
            user = obj.getString("user");
            password = obj.getString("password");
            token = obj.getString("token");

        } catch (JSONException e) {
        }


        String result = mAdm.editDisc(barcode, titel, director, fsk, description, token);
        return Response
                .status(STATUS200)
                .entity(result)
                .build();

    }



    /**
     * sets the MediaAcces interface this class uses.
     * @param mediaAccess the interface impelmentation to be used
     * @param user a dummy user inplace of a working user service
     */
    public static void setAccess(MediaAdminAccess mediaAccess, User user) {
        mAdm = mediaAccess;
        dummy = user;
    }}