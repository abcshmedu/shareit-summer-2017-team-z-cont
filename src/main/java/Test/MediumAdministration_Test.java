package Test;

import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.model.User;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Maximilian on 26.04.2017.
 */
public class MediumAdministration_Test {


    public void testAddBook(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        assertEquals(testedAdminstration.createBook("12", "testbook", "just a test", testUser), "invalid isbn");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "just a test", testUser), "OK");
        assertEquals(testedAdminstration.createBook("9783161484100", "testbook", "just a test", testUser), "exists already");
    }

    public void testAddDisc(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        assertEquals(testedAdminstration.createDisc("12", "testbook", "just a test", testUser), "invalid barcode");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testbook", "just a test", testUser), "OK");
        assertEquals(testedAdminstration.createDisc("9783161484100", "testbook", "just a test", testUser), "exists already");
    }


}
