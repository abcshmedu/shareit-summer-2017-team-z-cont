package edu.hm.REST;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import edu.hm.model.Book;
import edu.hm.model.User;


import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;

/** The Java class will be hosted at the URI path "/helloworld".
 *
 */
@Path("/media/books")
public class MediaBooks {


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
    public Response getAllBooks() {
        ArrayList<Book> retlist = new ArrayList<>();
        retlist = mAdm.getAllBooks();
        return Response
                .status(STATUS200)
                .entity(retlist.toString())
                .build();
    }

    /**
     * list all books with isbn.
     * @param isbn isbn
     * @return list of books with isbn
     */
    @GET
    @Path("{p}")
    public Response getBookByISBN(@PathParam("p") String isbn) {
        Book result;
        result = mAdm.findMediumByISBN(isbn);

        /*
        test for null in result
         */

        if (result != null) {
            return Response
                    .status(STATUS200)
                    .entity(result.toString())
                    .build();
        } else {
            return Response
                    .status(STATUS400)
                    .entity("no book found")
                    .build();
        }

    }

    /**
     * creates a new book.
     * @param dataMsg the data for the new book
     * @return a response
     */
    @POST
    @Consumes("application/json")
    public Response createBook(final String dataMsg) {

        JSONObject obj = new JSONObject(dataMsg);
        String isbn;
        String titel;
        String description;
        String author;

        String user;
        String password;
        User user1 = null;

        try {
            isbn = obj.getString("isbn");
            titel = obj.getString("titel");
            description = obj.getString("description");
            author = obj.getString("author");

            user = obj.getString("user");
            password = obj.getString("password");
            } catch (JSONException e) {
            return Response.status(STATUS400)
                    .entity("your json is invalid")
                    .build();
        }


        String result = mAdm.createBook(isbn, titel, "me", description, dummy);

        return Response
                .status(STATUS200)
                .entity(result)
                .build();
    }

    /**
     * updates a books data.
     * @param dataMsg contains the new data
     * @return  a response
     */
    @PUT
    @Consumes("application/json")
    public Response updateBook(final String dataMsg) {
        JSONObject obj = new JSONObject(dataMsg);

        String isbn = null;
        String titel = null;
        String author = null;
        String description = null;
        String user = null;
        String password = null;
        User user1 = null;


        try {
            isbn = obj.getString("isbn");
            titel = obj.getString("titel");
            author = obj.getString("author");
            description = obj.getString("description");
            user = obj.getString("user");
            password = obj.getString("password");
        } catch (JSONException e) {

        }


        String result = mAdm.editBook(isbn, titel, author, description, dummy);

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
    }
}