package edu.hm.Logic;

import com.google.inject.Inject;
import edu.hm.REST.UserAdminAccess;
import edu.hm.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class UserAdministartion implements UserAdminAccess {

    private static final long TOKEN_TIMEOUT = 60000;
    private static final int TOKEN_GEN_SEED = 300;
    private UserDataAccess userDataAccess;
    private Map<String, Long> validTokens = new HashMap<>();
    private Map<String, User> tokensToUser = new HashMap<>();


    /**
     * ctor for a new Useradministration.
     * @param dataAccess the acces to the (persistent) Userdata
     */
    @Inject
    public UserAdministartion(UserDataAccess dataAccess) {
        userDataAccess = dataAccess;
        createAdmin("AdminOne", "Admin");
    }

    @Override
    public User createUser(String username, String password) {
       return userDataAccess.addUser(username, password);
    }

    @Override
    public User createUser(String username, String password, String forname, String surname) {
        User newUser = userDataAccess.addUser(username, password);
        newUser.setForename(forname);
        newUser.setSurname(surname);
        userDataAccess.saveChanges();
        return newUser;
    }

    /**
     * creates a new Admin enabled User.
     * @param username the admins username
     * @param password the admisn password
     */
    private void createAdmin(String username, String password) {
        User newUser = userDataAccess.addUser(username, password);
        newUser.setAdmin(true);
        newUser.setActivated(true);
        userDataAccess.saveChanges();
    }

    @Override
    public String logIn(String username, String password) {
        String worked = "false";

            User userLoggingIn = userDataAccess.findUserByUsername(username);
            if (userLoggingIn.isActivated() && userLoggingIn.getPassword().equals(password)) {
                worked = generateToken();
                tokensToUser.put(worked, userLoggingIn);
            }

        return worked;
    }

    @Override
    public void logOut(String token) {
        validTokens.remove(token);
        tokensToUser.remove(token);
    }

    @Override
    public boolean makeAdmin(String username, String token) {
        boolean worked = false;
        if (checkToken(token)) {
            if (tokensToUser.get(token).isAdmin()) {
                userDataAccess.findUserByUsername(username).setAdmin(true);
                worked = true;
            }
        }
        userDataAccess.saveChanges();
        return worked;
    }

    @Override
    public boolean activateUser(String username, String token) {
        boolean worked = false;
        if (checkToken(token)) {
            if (tokensToUser.get(token).isAdmin()) {
                userDataAccess.findUserByUsername(username).setActivated(true);
                worked = true;
            }
        }
        userDataAccess.saveChanges();
        return worked;
    }

    @Override
    public User findUser(String username) {

        return userDataAccess.findUserByUsername(username);
    }

    @Override
    public ArrayList<User> getAllUsers() {

        return userDataAccess.getAllUsers();
    }

    @Override
    public ArrayList<User> getAllAdmins(String token) {
        ArrayList<User> result = new ArrayList<>();
        if (checkToken(token)) {
            if (tokensToUser.get(token).isAdmin()) {
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

    @Override
    public void changeForename(String forename, String token) {
        if (checkToken(token)) {
            tokensToUser.get(token).setForename(forename);
        }
        userDataAccess.saveChanges();
    }

    @Override
    public void changeSurname(String surname, String token) {
        if (checkToken(token)) {
            tokensToUser.get(token).setSurname(surname);
        }
        userDataAccess.saveChanges();
    }


    /**
     * generates a unique but random token.
     * @return the String token the was generated
     */
    private String generateToken() {
        String newToken =  new Random().nextInt(TOKEN_GEN_SEED) + "userToken" + System.currentTimeMillis();
        validTokens.put(newToken, System.currentTimeMillis());
        return newToken;
    }

    @Override
    public boolean checkToken(String token) {
        boolean isValid = false;
        if (validTokens.containsKey(token)) {
            if (System.currentTimeMillis() - validTokens.get(token) < TOKEN_TIMEOUT) {
                isValid = true;
                validTokens.replace(token, System.currentTimeMillis());
            } else {
                validTokens.remove(token);
                tokensToUser.remove(token);
            }
        }

        return isValid;
    }

    @Override
    public User getUserForToken(String token) {
        User returnedUser = null;
        if (checkToken(token)) {
            returnedUser = tokensToUser.get(token);
        }
        return returnedUser;
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
