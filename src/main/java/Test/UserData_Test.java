package Test;

import edu.hm.Data.UserData;
import edu.hm.Logic.UserAdministartion;
import edu.hm.REST.UserAPI;
import edu.hm.model.User;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

/**
 * Created by Maximilian on 26.04.2017.
 */
@SuppressWarnings("CheckStyle")
public class UserData_Test {


//    public void testLoginLogOut() {
//        UserData userData = new UserData();
//        UserAdministartion userAdministartion = new UserAdministartion(userData);
//        UserAPI testedUserApi = new UserAPI();
//        testedUserApi.setAccess(userAdministartion);
//        Response result = testedUserApi.login("{'user' : 'AdminOne', 'password' : 'Admin'}");
//        String token = result.getEntity().toString();
//        result = testedUserApi.logout("{'token' :'"+token+"'}");
//        assertEquals(true, result.getEntity().toString().equals("loggedout"));
//    }
//
//    public void testCreateActivateUser() {
//        UserData userData = new UserData();
//        UserAdministartion userAdministartion = new UserAdministartion(userData);
//        User testUser = new User("Username", "Passwort");
//        UserAPI testedUserApi = new UserAPI();
//        testedUserApi.setAccess(userAdministartion);
//        Response result = testedUserApi.createUser("{'user' : 'Username', 'password' : 'Passwort'}");
//        assertEquals(result.getEntity().toString(), testUser.toString());
//        Response Adminresult = testedUserApi.login("{'user' : 'AdminOne', 'password' : 'Admin'}");
//        String token = Adminresult.getEntity().toString();
//        testUser.setActivated(true);
//        Response activationResponse = testedUserApi.activateUser("{'user' : 'Username', 'token' : '"+token+"'}");
//        assertEquals(activationResponse.getEntity().toString(), "user activated");
//    }

}
