package edu.hm.REST;

import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediaAdminAccess {

    String createBook(String isbn, String titel, String author, String description,User curUser);
    String createDisc(String barcode, String titel, String description, User curUser);
    String createCopy(User curUser, Medium medium, String location);
    String editBook(String isbn, String titel, String author, String description,User curUser);
    String editDisc(String barcode, String titel, String description, User curUser);
    Book findMediumByISBN(String isbn);
    Disc findMediumByBarcode(String barcode);
    ArrayList<Copy> findCopyByMedium(Medium medium);
    ArrayList<Book> getAllBooks();
    ArrayList<Disc> getAllDiscs();
    ArrayList<Copy> findCopyByOwner(User owner);
    ArrayList<Medium> findMediumByTitel(String titel);
    ArrayList<Medium> findMediumByDescribtion(String desc);
    ArrayList<Copy> findCopyByLocation(String loc);
    ArrayList<Copy> findCopyByBorrower(User borrower);



}
