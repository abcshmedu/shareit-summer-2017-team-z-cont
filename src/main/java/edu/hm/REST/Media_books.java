package edu.hm.REST;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.model.Book;
import edu.hm.model.User;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/media/books")
public class Media_books {


    /**
     * create ctor to get the logic
     */
    private static MediaAdminAccess mAdm;


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
                .status(200)
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
    public Response getBookByISBN(@PathParam("p") String isbn){
        Book result;
        result = mAdm.findMediumByISBN(isbn);
        return Response
                    .status(200)
                    .entity(result.toString())
                    .build();

    }

    @POST
    @Consumes("application/json")
    public Response createBook(final String dataMsg){

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
            if(user != null && password != null){
                user1 = new User(user, password);
            } else {
                user1 = new User("dummy", "dummy");
            }        } catch (JSONException e) {
            return Response.status(400)
                    .entity("your json is invalid")
                    .build();
        }


        String result = mAdm.createBook(isbn, titel, "me", description, user1);

        return Response
                .status(200)
                .entity(result)
                .build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateBook(final String dataMsg){
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
            if(user != null && password != null){
                user1 = new User(user, password);
            } else {
                user1 = new User("dummy", "dummy");
            }
        } catch (JSONException e) {
            return Response
                    .status(401)
                    .entity("your json is invalid")
                    .build();
        }


        String result = mAdm.editBook(isbn, titel, author, description, user1);

        return Response
                .status(200)
                .entity(result)
                .build();

    }



        public static void setAccess(MediaAdminAccess mediaAccess) {
        mAdm = mediaAccess;
    }
}