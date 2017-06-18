package edu.hm.Data;

import edu.hm.Logic.UserDataAccess;
import edu.hm.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class UserData implements UserDataAccess {

    private static SessionFactory factory;
    private ArrayList<User> userList = new ArrayList<>();

    /**
     * ctor for a new UserData Object.
     */
    public UserData() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        updateUserList();
    }


    @Override
    public User addUser(String username, String password) {
        return addUsertoDatabase(username, password);
    }


    /**
     *
     * @param username the Username of the new User
     * @param password the new Users password
     * @return the new User
     */
    private User addUsertoDatabase(String username, String password) {
        Session session = factory.openSession();
        Transaction tx = null;
        User newUser = null;
        try {
            tx = session.beginTransaction();
            newUser = new User(username, password);
            session.save(newUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        // This will create the User twice
        //updateUserList();
        userList.add(newUser);
        return newUser;
    }

    /**
     * updates the userlist from the database.
     */
    public void updateUserList() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List tempUserList = session.createQuery("FROM User").list();
            for (Iterator iterator =
                 tempUserList.iterator(); iterator.hasNext();) {
                User user = (User) iterator.next();
                if (!this.userList.contains(user)) {
                    this.userList.add(user);
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * updates a Users data in the database.
     * @param updatedUser the updated User
     */
    public void updateUser(User updatedUser) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(updatedUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
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

    @Override
    public void saveChanges() {
        for (Iterator iterator =
             userList.iterator(); iterator.hasNext();) {
            User user = (User) iterator.next();
            updateUser(user);
        }
    }

}
