package Test;

import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.Medium;
import edu.hm.model.User;

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
    }

    public void testAddBook(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        assertEquals(testedAdminstration.createBook("12", "testbook", "just a test", testUser), "invalid isbn");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "just a test", testUser), "OK");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testbook", "just a test", testUser), "exists already");
        testUser.setActivated(false);
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "just a test", testUser), "not Authorized");
    }

    public void testAddDisc(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        assertEquals(testedAdminstration.createDisc("12", "testbook", "just a test", testUser), "invalid barcode");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "just a test", testUser), "OK");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "just a test", testUser), "exists already");
        testUser.setActivated(false);
        assertEquals(testedAdminstration.createDisc("9783161484100", "testDisc", "just a test", testUser), "not Authorized");
    }

    public void testAddCopy(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Medium original = new Book("9783161484100", "testbook", "just a test");
        assertEquals(testedAdminstration.createCopy(testUser, original, "here"), "OK");
        testUser.setActivated(false);
        assertEquals(testedAdminstration.createCopy(testUser, original, "here"), "not Authorized");
    }

    public void testSearch(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        Medium original = new Book("9783161484100", "testbook", "just a test");
        assertNull(testedAdminstration.findMediumByISBN("9783161484100"));
        assertNull(testedAdminstration.findMediumByBarcode("9783161484100"));
        assertTrue(testedAdminstration.findCopyByOwner(testUser).isEmpty());
        assertTrue(testedAdminstration.findCopyByLocation("here").isEmpty());
        assertTrue(testedAdminstration.findCopyByBorrower(testUser).isEmpty());
        assertTrue(testedAdminstration.findCopyByMedium(original).isEmpty());
        assertTrue(testedAdminstration.findMediumByDescribtion("just a test").isEmpty());
        assertTrue(testedAdminstration.findMediumByTitel("testDisc").isEmpty());
        assertTrue(testedAdminstration.getAllBooks().isEmpty());
        assertTrue(testedAdminstration.getAllDiscs().isEmpty());
        testedAdminstration.createBook("9783161484100", "testBook", "just a test", testUser);
        testedAdminstration.createDisc("9783161484100", "testDisc", "just a test", testUser);
        assertTrue(testedAdminstration.findMediumByISBN("9783161484100").equals(new Book("9783161484100", "testBook", "just a test")));
        assertTrue(testedAdminstration.findMediumByBarcode("9783161484100").equals(new Disc("9783161484100", "testDisc", "just a test")));

    }


}
