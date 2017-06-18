package Test;

import edu.hm.Data.MediumData;
import edu.hm.Data.UserData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.Logic.UserAdministartion;
import edu.hm.REST.MediaDisks;
import edu.hm.model.User;
import org.junit.Test;

import javax.ws.rs.core.Response;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Maximilian on 26.04.2017.
 */

@SuppressWarnings("CheckStyle")
public class RestDisk_Test {


    public static void main(String... args) {
        RestDisk_Test tester = new RestDisk_Test();
        tester.testAddDisk();
        tester.testGetAllDiscs();
        tester.testGetDiskByBarcode();
        tester.testUpdateDisk();

    }

    @Test
    public void testAddDisk() {
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);



        Response ok = Response.status(200).entity("OK").build();
        Response invJson = Response.status(400).entity("your json is invalid").build();
        Response barcodeExists = Response.status(200).entity("exists allready").build();


        Response got = discsTest.createDisc("{'barcode': '9783161484100', 'title' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(ok.toString()));


        got = discsTest.createDisc("{'title' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(invJson.toString()));

        got = discsTest.createDisc("{'barcode': '9783161484100', 'title' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.toString().equals(barcodeExists.toString()));
    }
    @Test
    public void testGetAllDiscs(){
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);

        discsTest.createDisc("{'barcode': '9783161484100', 'title' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        discsTest.createDisc("{'barcode': '5051890045188', 'title' : 'LOTR', 'director' : 'director', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        String currentDiscs = "[Medium: {'title':'test', 'description':'test desc'}, Medium: {'title':'LOTR', 'description':'LotR triology'}]";
        Response got = discsTest.getAllDiscs();
        assertTrue(got.getEntity().toString().equals(currentDiscs));


    }
    @Test
    public void testGetDiskByBarcode(){
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);

        String currentDisc = "Medium: {'title':'LOTR', 'description':'LotR triology'}";
        String noDiscFound = "no disc found";

        discsTest.createDisc("{'barcode': '5051890045188', 'title' : 'LOTR', 'director' : 'director', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        Response got = discsTest.getDiscByBarcode("5051890045188");

        assertTrue(got.getEntity().toString().equals(currentDisc));

        got = discsTest.getDiscByBarcode("5051890045189");
        assertTrue(got.getEntity().toString().equals(noDiscFound));
    }
    @Test
    public void testUpdateDisk() {
        UserData userData = new UserData();
        UserAdministartion userAdministartion = new UserAdministartion(userData);
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData, userAdministartion);
        User testUser = userAdministartion.createUser("Username", "Passwort");
        String adminToken = userAdministartion.logIn("AdminOne", "Admin");
        userAdministartion.activateUser("Username", adminToken);
        String token = userAdministartion.logIn("Username", "Passwort");
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);



        String ok = "OK";
        String noDiscFound = "no Disc found";
        String noTitle = "no Title";
        Response invJson = Response.status(200).entity("your json is invalid").build();
        discsTest.createDisc("{'barcode': '5051890045188', 'title' : 'LOTR', 'director' : 'director', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");

        //0ponse got = discsTest.getDiscByBarcode("5051890045188");
        Response got = discsTest.updateDisc("{'barcode': '5051890045188', 'title' : 'LOTR', 'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");

        assertTrue(got.getEntity().toString().equals(ok));

        got = discsTest.updateDisc("{'barcode': '9783161484100', 'title' : 'LOTR', 'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.getEntity().toString().equals(noDiscFound));

        got = discsTest.updateDisc("{'barcode': '5051890045188', 'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.getEntity().toString().equals(noTitle));


        got = discsTest.updateDisc("{'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw', 'token':'"+token+"'}");
        assertTrue(got.getEntity().toString().equals(noDiscFound));

    }


}

