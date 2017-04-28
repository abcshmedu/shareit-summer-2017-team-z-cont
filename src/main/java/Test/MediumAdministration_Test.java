package Test;

import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.model.*;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Maximilian on 26.04.2017.
 */
public class MediumAdministration_Test {


    public static void main(String... args){
        MediumAdministration_Test tester = new MediumAdministration_Test();
        tester.testAddBook();
        tester.testAddDisc();
        tester.testAddCopy();
        tester.testSearch();
        tester.testEdit();
    }

    public void testAddBook(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        assertEquals(testedAdminstration.createBook("12", "testbook", "me", "just a test", testUser), "invalid isbn");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "me", "just a test", testUser), "OK");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "me", "just a test", testUser), "exists already");
        testUser.setActivated(false);
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook",  "me", "just a test", testUser), "not Authorized");
    }

    public void testAddDisc(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        assertEquals(testedAdminstration.createDisc("12", "testDisc", "me", 1,"just a test", testUser), "invalid barcode");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1,"just a test", testUser), "OK");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1, "just a test", testUser), "exists already");
        testUser.setActivated(false);
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1,"just a test", testUser), "not Authorized");
    }

    public void testAddCopy(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Medium original = new Book("9783161484100", "testbook", "me", "just a test");
        assertEquals(testedAdminstration.createCopy(testUser, original, "here"), "OK");
        testUser.setActivated(false);
        assertEquals(testedAdminstration.createCopy(testUser, original, "here"), "not Authorized");
    }

    public void testSearch(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Book testBook = new Book("9783161484100", "testBook", "me", "just a test");
        Disc testDisc = new Disc("9783161484100", "testDisc",  "me", 1,"just a test");

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


        testedAdminstration.createBook("9783161484100", "testBook", "me","just a test", testUser);
        testedAdminstration.createDisc("9783161484100", "testDisc",  "me", 1, "just a test", testUser);

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

        testedAdminstration.createCopy(testUser, testBook, "here");
        assertTrue(testedAdminstration.findCopyByOwner(testUser).equals(testCopyList));
        assertTrue(testedAdminstration.findCopyByLocation("here").equals(testCopyList));
        assertTrue(testedAdminstration.findCopyByMedium(testBook).equals(testCopyList));

        testCopyList.get(0).borrowCopy(testUser);
        testedAdminstration.findCopyByMedium(testBook).get(0).borrowCopy(testUser);
        assertTrue(testedAdminstration.findCopyByBorrower(testUser).equals(testCopyList));
    }

    public void testEdit(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        testedAdminstration.createBook("9783161484100", "testBook", "me","just a test", testUser);
        testedAdminstration.createDisc("9783161484100", "testDisc", "me", 1, "just a test", testUser);

        assertEquals("OK", testedAdminstration.editBook("9783161484100", "nope", "notME", "just a description", testUser));
        assertEquals("OK", testedAdminstration.editBook("9783161484100", "testedBook", "notME", null, testUser));
        assertEquals("no Titel", testedAdminstration.editBook("9783161484100", null, "notME", "just a description", testUser));
        assertEquals("no Author", testedAdminstration.editBook("9783161484100", "what titel", null, "just a description", testUser));

        assertEquals("OK", testedAdminstration.editDisc("9783161484100", "nope",  "me", 1,"just a description", testUser));
        assertEquals("OK", testedAdminstration.editDisc("9783161484100", "testedBook", "me", 1, null, testUser));
        assertEquals("OK", testedAdminstration.editDisc("9783161484100", "testedBook", "me", 0, null, testUser));
        assertEquals("no Titel", testedAdminstration.editDisc("9783161484100", null, "me", 1, "just a description", testUser));
        assertEquals("no Director", testedAdminstration.editDisc("9783161484100", "shit", null, 1, "just a description", testUser));


    }


}
