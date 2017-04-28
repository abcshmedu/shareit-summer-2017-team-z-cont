package edu.hm.REST;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.User;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/media/discs")
public class Media_disks {


    /**
     * create ctor to get the logic
     */
    private static MediaAdminAccess mAdm;
    private static User dummy;


    /**
     * list all books.
     * @return List of all books.
     */
    @GET
    @Produces("application/json")
    public Response getAllDisvs() {
        ArrayList<Disc> retlist = new ArrayList<>();
        retlist = mAdm.getAllDiscs();
        return Response
                .status(200)
                .entity(retlist.toString())
                .build();
    }

    /**
     * list all books with isbn.
     * @param barcode barcode
     * @return list of books with isbn
     */
    @GET
    @Path("{p}")
    public Response getDiscByBarcode(@PathParam("p") String barcode){
        Disc result;
        result = mAdm.findMediumByBarcode(barcode);
        return Response
                    .status(200)
                    .entity(result.toString())
                    .build();

    }

    @POST
    @Consumes("application/json")
    public Response createDisc(final String dataMsg){

        JSONObject obj = new JSONObject(dataMsg);
        String barcode;
        String titel;
        String description;
        String user;
        String password;
        User user1 = null;

        try {
            barcode = obj.getString("barcode");
            titel = obj.getString("titel");
            description = obj.getString("description");
            user = obj.getString("user");
            password = obj.getString("password");

        } catch (JSONException e) {
            return Response.status(400)
                    .entity("your json is invalid")
                    .build();
        }


        String result = mAdm.createDisc(barcode, titel, description, dummy);
        return Response
                .status(200)
                .entity(result)
                .build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateDisc(final String dataMsg){
        JSONObject obj = new JSONObject(dataMsg);

        String barcode = null;
        String titel = null;
        String description = null;
        String user = null;
        String password = null;
        User user1 = null;


        try {
            barcode = obj.getString("isbn");
            titel = obj.getString("titel");
            description = obj.getString("description");
            user = obj.getString("user");
            password = obj.getString("password");

        } catch (JSONException e) {
        }


        String result = mAdm.editDisc(barcode, titel, description, dummy);
        return Response
                .status(200)
                .entity(result)
                .build();

    }



    public static void setAccess(MediaAdminAccess mediaAccess, User user) {
        mAdm = mediaAccess;
        dummy = user;
    }}