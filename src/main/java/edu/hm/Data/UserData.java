package edu.hm.Data;

import edu.hm.Logic.UserDataAccess;
import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class UserData implements UserDataAccess{


    private ArrayList<User> userList;

    public UserData () {
        userList = new ArrayList<>();
    }

    public UserData(ArrayList<User> oldUsers){
        userList=oldUsers;
    }

    public void addUser(String username, String password){
        userList.add(new User(username, password));
    }

    public User findUserByUsername (String username){
        User result = null;
        if(userList.size()>0){
            for (User u: userList) {
                if(u.getUsername()!=null&&u.getUsername().equals(username)){
                    result = u;
                }
            }
        }

        return result;
    }

}
