package edu.hm.Data;

import edu.hm.Logic.MediumDataAccess;
import edu.hm.model.*;
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
public class MediumData implements MediumDataAccess {

    private static SessionFactory factory;
    private ArrayList<Medium> mediaList;
    private ArrayList<Copy> copies;


    /**
     * creates a new MediumData ewith an empty MediaList.
     */
    public MediumData() {
        mediaList = new ArrayList<>();
        copies = new ArrayList<>();

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        updateCopyList();
        updateBookList();
        updateCopyList();
    }

    /**
     * creates a new MediumData with a pre existing MediaList.
     * @param mediaCatalogue an ArrayList Containing all Mediums the MediumData should know from the start
     */
    public MediumData(ArrayList<Medium> mediaCatalogue) {

        mediaList = mediaCatalogue;
    }

    @Override
    public String addDisc(String barcode, String titel, String director, int fsk, String description) {
        String answer = "exists already";
        Disc newDisc = new Disc(barcode, titel, director, fsk, description);
        boolean exists = false;
        for (Medium m : mediaList) {
            if (m.equals(newDisc)) {
                exists = true;
            }
        }
        if (!exists) {
            mediaList.add(addDisctoDatabase(newDisc));
            answer = "OK";
        }
        return answer;
    }

    @Override
    public Copy addCopy(User owner, Medium medium, String location) {
        Copy newCopy = new Copy(owner, medium, location);
        copies.add(addCopytoDatabase(newCopy));
        return newCopy;
    }

    @Override
    public String addBook(String isbn, String titel, String author, String description) {
        String answer = "exists already";
        Book newBook = new Book(isbn, titel, author, description);
        boolean exists = false;
        for (Medium m : mediaList) {
            if (m.equals(newBook)) {
                exists = true;
            }
        }
        if (!exists) {
            mediaList.add(addBooktoDatabase(newBook));
            answer = "OK";
        }
        return answer;
    }



    @Override
    public ArrayList<Medium> getMediaList() {
        return mediaList;
    }

    @Override
    public ArrayList<Copy> getCopyList() {
        return copies;
    }


    @Override
    public String toString() {
        // please also add a toString on a Medium
        return
                "mediaList="
                        + mediaList
                        + '}';
    }

    //Persitence methods


    /**
     * adds a new Book to the Database.
     * @param newBook the new book to be added
     * @return the added book
     */
    private Book addBooktoDatabase(Book newBook) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(newBook);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        updateBookList();
        return newBook;
    }

    /**
     * updates the mediumlist from the database with new books.
     */
    public void updateBookList() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List tempBookList = session.createQuery("FROM Book").list();
            for (Iterator iterator =
                 tempBookList.iterator(); iterator.hasNext();) {
                Book book = (Book) iterator.next();
                if (!mediaList.contains(book)) {
                    mediaList.add(book);
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
     * adds a new Copy to the Database.
     * @param newCopy the new copy to be added
     * @return the added copy
     */
    private Copy addCopytoDatabase(Copy newCopy) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(newCopy);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        updateCopyList();
        return newCopy;
    }

    /**
     * updates the copies from the database with new copies.
     */
    public void updateCopyList() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List tempCopyList = session.createQuery("FROM Copy").list();
            for (Iterator iterator =
                 tempCopyList.iterator(); iterator.hasNext();) {
                Copy copy = (Copy) iterator.next();
                if (!copies.contains(copy)) {
                    copies.add(copy);
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
     * adds a new Disc to the Database.
     * @param newDisc the new Disc to be added
     * @return the added disc
     */
    private Disc addDisctoDatabase(Disc newDisc) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(newDisc);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        updateDiscList();
        return newDisc;
    }

    /**
     * updates the mediumlist from the database with new books.
     */
    public void updateDiscList() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List tempDiscList = session.createQuery("FROM Disc").list();
            for (Iterator iterator =
                 tempDiscList.iterator(); iterator.hasNext();) {
                Disc disc = (Disc) iterator.next();
                if (!mediaList.contains(disc)) {
                    mediaList.add(disc);
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
     * updates a Mediums data in the database.
     * @param updatedMedium the updated User
     */
    public void updateMedium(Medium updatedMedium) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(updatedMedium);
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
     * updates a Mediums data in the database.
     * @param updatedCopy the updated User
     */
    public void updateCopy(Copy updatedCopy) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(updatedCopy);
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
    public void saveChanges() {
        for (Iterator iterator =
             mediaList.iterator(); iterator.hasNext();) {
            Medium medium = (Medium) iterator.next();
            updateMedium(medium);
        }
        for (Iterator iterator =
             copies.iterator(); iterator.hasNext();) {
            Copy copy = (Copy) iterator.next();
            updateCopy(copy);
        }
    }
}
