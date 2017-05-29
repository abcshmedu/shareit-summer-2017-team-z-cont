package edu.hm.Data;

import edu.hm.Logic.UserDataAccess;
import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class UserData implements UserDataAccess {


    private ArrayList<User> userList = new ArrayList<>();

    /**
     * ctor for a new UserData Object.
     */
    public UserData() {
        //userList = new ArrayList<>();
    }

    /**
     * ctor for a UserData with existing users.
     * @param oldUsers an array list of persistent users
     */
    public UserData(ArrayList<User> oldUsers) {

        userList = oldUsers;
    }

    @Override
    public User addUser(String username, String password) {
        User newUser = new User(username, password);
        userList.add(newUser);
        return newUser;
    }

    @Override
    public User findUserByUsername(String username) {
        User result = null;
        if (userList.size() > 0) {
            for (User u: userList) {
                if (u.getUsername() != null && u.getUsername().equals(username)) {
                    result = u;
                }
            }
        }

        return result;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return userList;
    }


}
