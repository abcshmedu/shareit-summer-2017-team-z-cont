package Test;

import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.REST.Media_books;
import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.Medium;
import edu.hm.model.User;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Maximilian on 26.04.2017.
 */
public class RestBook_Test {


    public static void main(String... args) {
        RestBook_Test tester = new RestBook_Test();
        tester.testAddBook();
        tester.testGetAllBooks();
        tester.testGetBookByISBN();
        tester.testUpdateBook();

    }


    public void testAddBook() {
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Media_books booksTest = new Media_books();
        booksTest.setAccess(testedAdminstration, testUser);

        Response ok = Response.status(200).entity("OK").build();
        Response invJson = Response.status(400).entity("your json is invalid").build();
        Response isbnExists = Response.status(200).entity("exists allready").build();


        Response got = booksTest.createBook("{'isbn': '9783161484100', 'titel' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(ok.toString()));
        got = booksTest.createBook("{'titel' : 'test', 'description' : 'testdesc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(invJson.toString()));
        got = booksTest.createBook("{'isbn': '9783161484100', 'titel' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(isbnExists.toString()));
    }

    public void testGetAllBooks(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Media_books booksTest = new Media_books();
        booksTest.createBook("{'isbn': '9788373191723', 'titel' : 'HdR', 'description' : 'HdR', 'author' : 'testauth', 'user':'testuser', 'password':'testpw'}");

        String currentbooks = "[Medium{, titel='test', description='test desc'}, Medium{, titel='HdR', description='HdR'}]";
        String got = booksTest.getAllBooks().getEntity().toString();
        assertTrue(got.equals(currentbooks));
    }

    public void testGetBookByISBN(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Media_books booksTest = new Media_books();

        String book = "Medium{, titel='HdR', description='HdR'}";
        String got = booksTest.getBookByISBN("9788373191723").getEntity().toString();
        assertTrue(got.equals(book));

        /*
        TODO: test for wrong ISBN here and also in the method.
         */
        //got = booksTest.getBookByISBN("9888373191723").getEntity().toString();
        //System.out.println(got);
    }

    public void testUpdateBook() {
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Media_books booksTest = new Media_books();
        booksTest.setAccess(testedAdminstration, testUser);

        Response ok = Response.status(200).entity("OK").build();
        Response invJson = Response.status(200).entity("your json is invalid").build();
        Response isbnExists = Response.status(200).entity("exists allready").build();
        Response got = booksTest.updateBook("{'isbn': '9783161484100', 'titel' : 'test-modify', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(ok.toString()));
        got = booksTest.updateBook("{'titel' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(invJson.toString()));
        got = booksTest.updateBook("{'isbn': '978316148412200', 'titel' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(ok.toString()));

    }


}

