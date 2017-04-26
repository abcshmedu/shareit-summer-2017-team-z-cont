package edu.hm.REST;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.model.Book;


import java.util.ArrayList;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/media/books")
public class media_books {

    private MediumAdministartion mAdm = new MediumAdministartion();


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
        ArrayList<Book> retlist = new ArrayList<>();
        retlist = mAdm.findMediumByISBN(isbn);
        return Response
                    .status(200)
                    .entity(retlist.toString())
                    .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(){

    }
}