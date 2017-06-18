package Test;

import edu.hm.Data.MediumData;
import edu.hm.Data.UserData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.Logic.UserAdministartion;
import edu.hm.REST.MediaBooks;
import edu.hm.model.User;
import org.junit.Test;

import javax.ws.rs.core.Response;


import static junit.framework.TestCase.assertTrue;

/**
 * Created by Maximilian on 26.04.2017.
 */
@SuppressWarnings("CheckStyle")
public class RestBook_Test {


    public static void main(String... args) {
        RestBook_Test tester = new RestBook_Test();
        tester.testAddBook();
        tester.testGetAllBooks();
        tester.testGetBookByISBN();
        tester.testUpdateBook();

    }

    @Test
    public void testAddBook() {
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mdata = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mdata, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaBooks booksTest = new MediaBooks();
        booksTest.setAccess(testedAdminstration, testUser);

        Response ok = Response.status(200).entity("OK").build();
        Response invJson = Response.status(400).entity("your json is invalid").build();
        Response isbnExists = Response.status(200).entity("exists allready").build();


        Response got = booksTest.createBook("{'isbn': '9783161484100', 'title' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(ok.toString()));
        got = booksTest.createBook("{'title' : 'test', 'description' : 'testdesc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(invJson.toString()));
        got = booksTest.createBook("{'isbn': '9783161484100', 'title' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(isbnExists.toString()));
    }
    @Test
    public void testGetAllBooks(){
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaBooks booksTest = new MediaBooks();
        booksTest.createBook("{'isbn': '9788373191723', 'title' : 'HdR', 'description' : 'HdR', 'author' : 'testauth', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        String currentbooks = "[Medium: {'title':'test', 'description':'test desc'}, Medium: {'title':'HdR', 'description':'HdR'}]";
        String got = booksTest.getAllBooks().getEntity().toString();
        System.out.print(got);
        got = got.substring(got.indexOf("["));
        System.out.print(got);
        assertTrue(got.equals(currentbooks));
    }
    @Test
    public void testGetBookByISBN(){
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaBooks booksTest = new MediaBooks();

        String book = "Medium: {'title':'HdR', 'description':'HdR'}";
        String got = booksTest.getBookByISBN("9788373191723").getEntity().toString();
        assertTrue(got.equals(book));

        String noBook = "no book found";
        got = booksTest.getBookByISBN("9888373191723").getEntity().toString();
        assertTrue(got.equals(noBook));
    }
    @Test
    public void testUpdateBook() {
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaBooks booksTest = new MediaBooks();
        booksTest.setAccess(testedAdminstration, testUser);

        Response ok = Response.status(200).entity("OK").build();
        Response invJson = Response.status(200).entity("your json is invalid").build();
        Response isbnExists = Response.status(200).entity("exists allready").build();
        Response got = booksTest.updateBook("{'isbn': '9783161484100', 'title' : 'test-modify', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(ok.toString()));
        got = booksTest.updateBook("{'title' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(invJson.toString()));
        got = booksTest.updateBook("{'isbn': '978316148412200', 'title' : 'test', 'description' : 'test desc', 'author' : 'testauth', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(ok.toString()));

    }


}

