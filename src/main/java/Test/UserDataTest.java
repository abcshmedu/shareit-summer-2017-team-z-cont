package Test;

import edu.hm.Data.UserData;
import edu.hm.model.*;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Maximilian on 18.06.2017.
 */
@SuppressWarnings("CheckStyle")
public class UserDataTest {

    @Test
    public void testAdd(){
        UserData userData = new UserData();
        User testUser =  new User("tester", "testPassword");
        assertEquals(userData.addUser("tester", "testPassword"),testUser);
        assertEquals(userData.findUserByUsername("tester"), testUser);
        ArrayList<User> testList = new ArrayList();
        testList.add(testUser);
        assertEquals(userData.getAllUsers(),testList);

    }
}
