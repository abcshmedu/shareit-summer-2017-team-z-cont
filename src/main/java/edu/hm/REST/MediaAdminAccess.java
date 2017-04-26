package edu.hm.REST;

import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediaAdminAccess {

    String createBook(String isbn, String titel, String description,User curUser);
    String createDisc(int barcode, String titel, String description, User curUser);
    String createCopy(User curUser, Medium medium, String location);
    Book findMediumByISBN(String isbn);
    Disc findMediumByBarcode(int barcode);
    ArrayList<Copy> findCopyByMedium(Medium medium);
    ArrayList<Book> getAllBooks();
    ArrayList<Disc> getAllDiscs();
    ArrayList<Copy> findCopyByOwner(User owner);
    ArrayList<Medium> findMediumByTitel(String titel);
    ArrayList<Medium> findMediumByDescribtion(String desc);
    ArrayList<Copy> findCopyByLocation(String loc);
    ArrayList<Copy> findCopyByBorrower(User borrower);


}
