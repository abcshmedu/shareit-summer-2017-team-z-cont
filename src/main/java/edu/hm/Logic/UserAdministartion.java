package edu.hm.Logic;

import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class UserAdministartion {

    private UserDataAccess userDataAccess;
    private User currrentUser;

    public UserAdministartion(UserDataAccess dataAccess){
        userDataAccess = dataAccess;
        createAdmin("AdminOne","Admin");
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

    public boolean logIn(String username, String password){
        boolean worked = false;
        if(currrentUser == null) {
            User userLoggingIn = userDataAccess.findUserByUsername(username);
            if (userLoggingIn.isActivated()&&userLoggingIn.getPassword().equals(password)) {
                currrentUser = userLoggingIn;
                worked = true;
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

    /**
    public boolean changePassword(User self, String passwordOld, String passwordNew){
        boolean worked = false;

        if(self.getPassword().equals(passwordOld)){
            self.setPassword(PasswordNew)
        }

        return worked;
    } **/
}
