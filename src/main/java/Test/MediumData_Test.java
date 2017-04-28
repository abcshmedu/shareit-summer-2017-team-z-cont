package Test;

import edu.hm.Data.MediumData;
import edu.hm.model.*;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Maximilian on 26.04.2017.
 */
public class MediumData_Test {

    public void testAdds(){
        MediumData mediumData = new MediumData();
        assertEquals(mediumData.addMedium("9783161484100", "testbook", "just a test"), "OK");
        assertEquals(mediumData.addMedium("9783161484100", "testbook", "just a test"), "exists already");
        assertEquals(mediumData.addDisc("9783161484100", "testDisc", "just a test"), "OK");
        assertEquals(mediumData.addDisc("9783161484100", "testDisc", "just a test"), "exists already");
        Medium original = new Book("9783161484100", "testbook", "just a test");
        User testUser = new User("Username", "Passwort");
        assertEquals(mediumData.addCopy(testUser, original, "here"), "OK");

    }

    public void testSearch(){

        MediumData mediumData = new MediumData();
        mediumData.addMedium("9783161484100", "testbook", "just a test");
        mediumData.addDisc("9783161484100", "testDisc", "just a test");
        ArrayList<Medium> expectedList = new ArrayList();
        expectedList.add(new Book("9783161484100", "testbook", "just a test"));
        expectedList.add(new Disc("9783161484100", "testDisc", "just a test"));
        assertEquals(mediumData.getMediaList(), expectedList);
        assertTrue(mediumData.getCopyList().isEmpty());
        mediumData.addCopy(new User("Username", "Passwort"), expectedList.get(0), "none");
        ArrayList<Copy> expectedCopies = new ArrayList<>();
        expectedCopies.add(new Copy(new User("Username", "Passwort"), expectedList.get(0), "none"));
        assertEquals(mediumData.getCopyList(), expectedCopies);

    }
}
