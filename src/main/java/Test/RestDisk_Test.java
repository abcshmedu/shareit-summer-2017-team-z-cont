package Test;

import edu.hm.Data.MediumData;
import edu.hm.Logic.MediumAdministartion;
import edu.hm.REST.MediaDisks;
import edu.hm.model.User;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Maximilian on 26.04.2017.
 */
public class RestDisk_Test {


    public static void main(String... args) {
        RestDisk_Test tester = new RestDisk_Test();
        tester.testAddDisk();
        tester.testGetAllDiscs();
        tester.testGetDiskByBarcode();
        tester.testUpdateDisk();

    }


    public void testAddDisk() {
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);



        Response ok = Response.status(200).entity("OK").build();
        Response invJson = Response.status(400).entity("your json is invalid").build();
        Response barcodeExists = Response.status(200).entity("exists allready").build();


        Response got = discsTest.createDisc("{'barcode': '9783161484100', 'titel' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(ok.toString()));


        got = discsTest.createDisc("{'titel' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(invJson.toString()));

        got = discsTest.createDisc("{'barcode': '9783161484100', 'titel' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.toString().equals(barcodeExists.toString()));
    }

    public void testGetAllDiscs(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);

        discsTest.createDisc("{'barcode': '9783161484100', 'titel' : 'test', 'director' : 'testdirector', 'fsk': '6', 'description' : 'test desc', 'user':'testuser', 'password':'testpw'}");
        discsTest.createDisc("{'barcode': '5051890045188', 'titel' : 'LOTR', 'director' : 'director', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw'}");
        String currentDiscs = "[Medium: {'titel':'test', 'description':'test desc'}, Medium: {'titel':'LOTR', 'description':'LotR triology'}]";
        Response got = discsTest.getAllDiscs();
        assertTrue(got.getEntity().toString().equals(currentDiscs));


    }

    public void testGetDiskByBarcode(){
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);

        String currentDisc = "Medium: {'titel':'LOTR', 'description':'LotR triology'}";
        String noDiscFound = "no disc found";

        discsTest.createDisc("{'barcode': '5051890045188', 'titel' : 'LOTR', 'director' : 'director', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw'}");
        Response got = discsTest.getDiscByBarcode("5051890045188");

        assertTrue(got.getEntity().toString().equals(currentDisc));

        got = discsTest.getDiscByBarcode("5051890045189");
        assertTrue(got.getEntity().toString().equals(noDiscFound));
    }

    public void testUpdateDisk() {
        MediumData mediumData = new MediumData();
        MediumAdministartion testedAdminstration = new MediumAdministartion(mediumData);
        User testUser = new User("Username", "Passwort");
        testUser.setActivated(true);
        MediaDisks discsTest = new MediaDisks();
        discsTest.setAccess(testedAdminstration, testUser);



        String ok = "OK";
        String noDiscFound = "no Disc found";
        String noTitle = "no Titel";
        Response invJson = Response.status(200).entity("your json is invalid").build();
        discsTest.createDisc("{'barcode': '5051890045188', 'titel' : 'LOTR', 'director' : 'director', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw'}");

        //0ponse got = discsTest.getDiscByBarcode("5051890045188");
        Response got = discsTest.updateDisc("{'barcode': '5051890045188', 'titel' : 'LOTR', 'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw'}");

        assertTrue(got.getEntity().toString().equals(ok));

        got = discsTest.updateDisc("{'barcode': '9783161484100', 'titel' : 'LOTR', 'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.getEntity().toString().equals(noDiscFound));

        got = discsTest.updateDisc("{'barcode': '5051890045188', 'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.getEntity().toString().equals(noTitle));


        got = discsTest.updateDisc("{'director' : 'director-modified', 'fsk': '16', 'description' : 'LotR triology', 'user':'testuser', 'password':'testpw'}");
        assertTrue(got.getEntity().toString().equals(noDiscFound));

    }


}

