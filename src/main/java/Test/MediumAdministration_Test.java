package Test;

import edu.hm.Data.MediumData;
import edu.hm.Data.UserData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.Logic.UserAdministartion;
import edu.hm.model.*;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Maximilian on 26.04.2017.
 */
@SuppressWarnings("CheckStyle")
public class MediumAdministration_Test {


    public static void main(String... args){
        MediumAdministration_Test tester = new MediumAdministration_Test();
        tester.testAddBook();
        tester.testAddDisc();
        tester.testAddCopy();
        tester.testSearch();
        tester.testEdit();
    }
    @Test
    public void testAddBook(){
        MediumData mediumData = new MediumData();
        UserData UserData = new UserData();
        UserAdministartion validate = new UserAdministartion(UserData);
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, validate);
        User testUser = validate.createUser("Username", "Passwort");
        String testToken = validate.logIn("Username", "Passwort");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook",  "me", "just a test", testToken), "not Authorized");
        String adminToken = validate.logIn("AdminOne", "Admin");
        validate.activateUser("Username", adminToken);
        testToken = validate.logIn("Username", "Passwort");
        assertEquals(testedAdminstration.createBook("12", "testbook", "me", "just a test", testToken), "invalid isbn");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "me", "just a test", testToken), "OK");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "me", "just a test", testToken), "exists already");
    }
    @Test
    public void testAddDisc(){
        MediumData mediumData = new MediumData();
        UserData UserData = new UserData();
        UserAdministartion validate = new UserAdministartion(UserData);
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, validate);
        User testUser = validate.createUser("Username", "Passwort");
        String testToken = validate.logIn("Username", "Passwort");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1, "just a test", testToken), "not Authorized");
        String adminToken = validate.logIn("AdminOne", "Admin");
        validate.activateUser("Username", adminToken);
        testToken = validate.logIn("Username", "Passwort");
        assertEquals(testedAdminstration.createDisc("12", "testDisc", "me", 1, "just a test", testToken), "invalid barcode");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1, "just a test", testToken), "OK");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1, "just a test", testToken), "exists already");

    }
    @Test
    public void testAddCopy(){
        MediumData mediumData = new MediumData();
        UserData UserData = new UserData();
        UserAdministartion validate = new UserAdministartion(UserData);
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, validate);
        User testUser = validate.createUser("Username", "Passwort");
        String testToken = validate.logIn("Username", "Passwort");
        Medium original = new Book("9783161484100", "testbook", "me", "just a test");
        assertEquals(testedAdminstration.createCopy(testToken, original, "here"), "not Authorized");
        String adminToken = validate.logIn("AdminOne", "Admin");
        validate.activateUser("Username", adminToken);
        System.out.print(testToken+"fuck this");
        testToken = validate.logIn("Username", "Passwort");
        assertEquals(testedAdminstration.createCopy(testToken, original, "here"), "OK");
    }

    @Test
    public void testSearch(){
        MediumData mediumData = new MediumData();
        UserData UserData = new UserData();
        UserAdministartion validate = new UserAdministartion(UserData);
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, validate);
        User testUser = validate.createUser("Username", "Passwort");
        String adminToken = validate.logIn("AdminOne", "Admin");
        validate.activateUser("Username", adminToken);
        String testToken = validate.logIn("Username", "Passwort");
        Book testBook = new Book("9783161484100", "testBook", "me", "just a test");
        Disc testDisc = new Disc("9783161484100", "testDisc",  "me", 1, "just a test");

        assertNull(testedAdminstration.findMediumByISBN("9783161484100"));
        assertNull(testedAdminstration.findMediumByBarcode("9783161484100"));
        assertTrue(testedAdminstration.findCopyByOwner(testUser).isEmpty());
        assertTrue(testedAdminstration.findCopyByLocation("here").isEmpty());
        assertTrue(testedAdminstration.findCopyByBorrower(testUser).isEmpty());
        assertTrue(testedAdminstration.findCopyByMedium(testBook).isEmpty());
        assertTrue(testedAdminstration.findMediumByDescribtion("just a test").isEmpty());
        assertTrue(testedAdminstration.findMediumByTitel("testDisc").isEmpty());
        assertTrue(testedAdminstration.getAllBooks().isEmpty());
        assertTrue(testedAdminstration.getAllDiscs().isEmpty());


        testedAdminstration.createBook("9783161484100", "testBook", "me", "just a test", testToken);
        testedAdminstration.createDisc("9783161484100", "testDisc",  "me", 1, "just a test", testToken);

        ArrayList<Medium> testMedia = new ArrayList();
        testMedia.add(testBook);
        testMedia.add(testDisc);
        ArrayList<Book> testBookList = new ArrayList<>();
        testBookList.add(testBook);
        ArrayList<Disc> testDiscList = new ArrayList<>();
        testDiscList.add(testDisc);

        assertTrue(testedAdminstration.findMediumByISBN("9783161484100").equals(testBook));
        assertTrue(testedAdminstration.findMediumByBarcode("9783161484100").equals(testDisc));
        assertTrue(testedAdminstration.findMediumByDescribtion("just a test").equals(testMedia));
        assertTrue(testedAdminstration.findMediumByTitel("testDisc").equals(testDiscList));
        assertTrue(testedAdminstration.getAllBooks().equals(testBookList));
        assertTrue(testedAdminstration.getAllDiscs().equals(testDiscList));

        Copy testCopy = new Copy(testUser, testBook, "here");
        ArrayList<Copy> testCopyList = new ArrayList<>();
        testCopyList.add(testCopy);

        testedAdminstration.createCopy(testToken, testBook, "here");
        System.out.print(testedAdminstration.findCopyByOwner(testUser) + "\n" + testCopyList);
        assertTrue(testedAdminstration.findCopyByOwner(testUser).equals(testCopyList));
        assertTrue(testedAdminstration.findCopyByLocation("here").equals(testCopyList));
        assertTrue(testedAdminstration.findCopyByMedium(testBook).equals(testCopyList));

        testCopyList.get(0).borrowCopy(testUser);
        testedAdminstration.findCopyByMedium(testBook).get(0).borrowCopy(testUser);
        assertTrue(testedAdminstration.findCopyByBorrower(testUser).equals(testCopyList));
    }

    @Test
    public void testEdit(){
        MediumData mediumData = new MediumData();
        UserData UserData = new UserData();
        UserAdministartion validate = new UserAdministartion(UserData);
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, validate);
        User testUser = validate.createUser("Username", "Passwort");
        String adminToken = validate.logIn("AdminOne", "Admin");
        validate.activateUser("Username", adminToken);
        String testToken = validate.logIn("Username", "Passwort");
        testedAdminstration.createBook("9783161484100", "testBook", "me", "just a test", testToken);
        testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1, "just a test", testToken);

        assertEquals("OK", testedAdminstration.editBook("9783161484100", "nope", "notME", "just a description", testToken));
        assertEquals("OK", testedAdminstration.editBook("9783161484100", "testedBook", "notME", null, testToken));
        assertEquals("no Titel", testedAdminstration.editBook("9783161484100", "", "notME", "just a description", testToken));
        assertEquals("no Author", testedAdminstration.editBook("9783161484100", "what titel", null, "just a description", testToken));

        assertEquals("OK", testedAdminstration.editDisc("9783161484100", "nope",  "me", 1, "just a description", testToken));
        assertEquals("OK", testedAdminstration.editDisc("9783161484100", "testedBook", "me", 1, null, testToken));
        assertEquals("OK", testedAdminstration.editDisc("9783161484100", "testedBook", "me", 0, null, testToken));
        assertEquals("no Titel", testedAdminstration.editDisc("9783161484100", null, "me", 1, "just a description", testToken));
        assertEquals("no Director", testedAdminstration.editDisc("9783161484100", "shit", null, 1, "just a description", testToken));

        assertEquals("no Book found", testedAdminstration.editBook("9783161485100", "what", "notME", "just a description", testToken));
        assertEquals("no Disc found", testedAdminstration.editDisc("9783161485100", "why", "me", 1, "just a description", testToken));

    }


}
