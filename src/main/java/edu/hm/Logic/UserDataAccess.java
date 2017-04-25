package edu.hm.Logic;

import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface UserDataAccess {

    User addUser(String username, String password);
    User findUserByUsername (String username);
    ArrayList<User> getAllUsers();
}
