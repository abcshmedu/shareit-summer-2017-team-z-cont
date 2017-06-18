package Test;

import edu.hm.Data.UserData;
import edu.hm.Logic.UserAdministartion;
import edu.hm.model.User;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Maximilian on 26.04.2017.
 */
@SuppressWarnings("CheckStyle")
public class UserAdministration_Test {

    public static void main(String... args){
        UserAdministration_Test Tester = new UserAdministration_Test();
        Tester.testFindUserCreateUser();
        Tester.testLoginLogout();
        Tester.testMakeAdminActivateUser();
        Tester.testUserEditing();
    }

    @Test
    public void testFindUserCreateUser(){
        UserData userData = new UserData();
        UserAdministartion userAdmin = new UserAdministartion(userData);
        userAdmin.createUser("tester", "testPassword");
        User testUser =  new User("tester", "testPassword");
        assertEquals(userAdmin.findUser("tester"), testUser);
        ArrayList<User> userList = new ArrayList<>();
        userList.add(userData.findUserByUsername("AdminOne"));
        userList.add(testUser);
        assertEquals(userAdmin.getAllUsers(), userList);
        userAdmin.createUser("second", "why", "Hans", "Lustig");
        User second = new User("second", "why");
        userList.add(second);
        second.setForename("Hans");
        second.setSurname("Lustig");
        assertEquals(userAdmin.getAllUsers(), userList);
    }
    @Test
    public void testLoginLogout(){
        UserData userData = new UserData();
        UserAdministartion userAdmin = new UserAdministartion(userData);
        String token = userAdmin.logIn("AdminOne", "Admin");
        assertEquals(true, userAdmin.checkToken(token));
        userAdmin.logOut(token);
        assertEquals(false, userAdmin.checkToken(token));
    }
    @Test
    public void testMakeAdminActivateUser(){
        UserData userData = new UserData();
        UserAdministartion userAdmin = new UserAdministartion(userData);
        userAdmin.createUser("tester", "testPassword");
        String token = userAdmin.logIn("AdminOne", "Admin");
        assertEquals(true, userAdmin.activateUser("tester", token));
        assertEquals(true, userAdmin.makeAdmin("tester", token));
        assertEquals(2, userAdmin.getAllAdmins(token).size());
    }
    @Test
    public void testUserEditing(){
        UserData userData = new UserData();
        UserAdministartion userAdmin = new UserAdministartion(userData);
        String token = userAdmin.logIn("AdminOne", "Admin");
        userAdmin.changeForename("peter", token);
        userAdmin.changeSurname("wurst", token);
        User admin = new User("AdminOne", "Admin");
        admin.setForename("peter");
        admin.setSurname("wurst");
        admin.setActivated(true);
        admin.setAdmin(true);
        assertEquals(admin, userAdmin.findUser("AdminOne"));
    }


}
