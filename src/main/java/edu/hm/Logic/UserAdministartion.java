package edu.hm.Logic;

import edu.hm.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class UserAdministartion {

    private static final long TOKEN_TIMEOUT = 60000;
    private static final int TOKEN_GEN_SEED = 300;
    private UserDataAccess userDataAccess;
    private User currrentUser;
    private Map<String, Long> validTokens;
    public UserAdministartion(UserDataAccess dataAccess){
        userDataAccess = dataAccess;
        createAdmin("AdminOne", "Admin");
    }

    public User createUser(String username, String password){
       return userDataAccess.addUser(username, password);
    }

    public User createUser(String username, String password, String forname, String surname){
        User newUser = userDataAccess.addUser(username, password);
        newUser.setForename(forname);
        newUser.setSurname(surname);
        return newUser;
    }

    private void createAdmin(String username, String password) {
        User newUser = userDataAccess.addUser(username, password);
        newUser.setAdmin(true);
        newUser.setActivated(true);
    }

    public String logIn(String username, String password){
        String worked = "false";
        if(currrentUser == null) {
            User userLoggingIn = userDataAccess.findUserByUsername(username);
            if (userLoggingIn.isActivated() && userLoggingIn.getPassword().equals(password)) {
                currrentUser = userLoggingIn;
                worked = generateToken();
            }
        }
        return worked;
    }

    public void logOut(){
        currrentUser = null;
    }

    public boolean makeAdmin(String username){
        boolean worked = false;
        if(currrentUser!=null) {
            if (currrentUser.isAdmin()) {
                userDataAccess.findUserByUsername(username).setAdmin(true);
                worked = true;
            }
        }
        return worked;
    }

    public boolean activateUser(String username){
        boolean worked = false;
        if(currrentUser!=null) {
            if (currrentUser.isAdmin()) {
                userDataAccess.findUserByUsername(username).setActivated(true);
                worked = true;
            }
        }
        return worked;
    }

    public User findUser(String username){
        return userDataAccess.findUserByUsername(username);
    }

    public ArrayList<User> getAllUsers(){
        return userDataAccess.getAllUsers();
    }

    public ArrayList<User> getAllAdmins(){
        ArrayList<User> result = new ArrayList<>();
        if(currrentUser!=null) {
            if (currrentUser.isAdmin()) {
                result = getAllUsers();
                for (User u : result) {
                    if (!u.isAdmin()) {
                        result.remove(u);
                    }
                }
            }
        }
       return result;
    }

    public void changeForename(String forename){
        if(currrentUser!=null) {
            currrentUser.setForename(forename);
        }
    }

    public void changeSurname(String surname){
        if(currrentUser!=null) {
            currrentUser.setSurname(surname);
        }
    }


    private String generateToken(){
        String newToken =  new Random().nextInt(TOKEN_GEN_SEED) + "userToken" + System.currentTimeMillis();
        validTokens.put(newToken, System.currentTimeMillis());
        return newToken;
    }

    public boolean checkToken(String token){
        boolean isValid = false;
        if (validTokens.containsKey(token)){
            if (System.currentTimeMillis() - validTokens.get(token) < TOKEN_TIMEOUT){
                isValid = true;
                validTokens.replace(token, System.currentTimeMillis());
            } else {
                validTokens.remove(token);
            }
        }

        return isValid;
    }

    /**
    public boolean changePassword(User self, String passwordOld, String passwordNew){
        boolean worked = false;

        if(self.getPassword().equals(passwordOld)){
            self.setPassword(PasswordNew)
        }

        return worked;
    } **/
}
