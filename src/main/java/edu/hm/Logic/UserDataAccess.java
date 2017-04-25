package edu.hm.Logic;

import edu.hm.model.User;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface UserDataAccess {

    void addUser(String username, String password);
    User findUserByUsername (String username);
}
