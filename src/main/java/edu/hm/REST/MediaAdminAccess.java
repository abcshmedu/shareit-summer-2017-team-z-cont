package edu.hm.REST;

import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediaAdminAccess {


    /**
     * Create a new book with the given values.
     * @param isbn the books unique isbn
     * @param titel the books titel
     * @param author the books author
     * @param description a short description
     * @param token the validation token of the user
     * @return a String containing a response either OK or a short error message
     */
    String createBook(String isbn, String titel, String author, String description, String token);

    /**
     * Create a new Disc.
     * @param barcode the Discs unique Barcode
     * @param titel the Discs Title
     * @param director the Contents director
     * @param fsk the fsk for the discs contents
     * @param description a short description
     * @param token the validation token of the user
     * @return a String containing a response either OK or a short error message
     */
    String createDisc(String barcode, String titel, String director, int fsk, String description, String token);

    /**
     * Creates a copy of a medium.
     * @param token the validation token of the user
     * @param medium the Medium the copy is of
     * @param location where the Copy can be collected
     * @return a String contaaining a response either OK or a short error message
     */
    String createCopy(String token, Medium medium, String location);

    /**
     * edit the Data of a book.
     * @param isbn the isbn of the book that will be edited
     * @param titel the new Titel or the old one if it shouldn't change
     * @param author the new Author of the book, or the old one if it shouldn't change
     * @param description the new discription, set null for no change
     * @param token the validation token of the user
     * @return a String contaaining a response either OK or a short error message
     */
    String editBook(String isbn, String titel, String author, String description, String token);

    /**
     * edit the Data of a Disc.
     * @param barcode the barcode of the Disc that will be edited
     * @param titel  the new Titel of the Dis, enter old TItel for no change
     * @param director the new Director of the disc, enter old director for no change
     * @param fsk the new fsk of the disc, enter old one for no change
     * @param description the new desription of the disc, enter null for no change
     * @param token the validation token of the user
     * @return a String contaaining a response either OK or a short error message
     */
    String editDisc(String barcode, String titel, String director, int fsk, String description, String token);

    /**
     * finds a book by its unique isbn.
     * @param isbn the wanted books isbn
     * @return the requested book or null if it doesn't exist yet
     */
    Book findMediumByISBN(String isbn);

    /**
     * finds a Disc by its unique Barcode.
     * @param barcode the wanted Discs barcode
     * @return the requested Disc or null if it doesn't exist yet
     */
    Disc findMediumByBarcode(String barcode);

    /**
     * finds a list of copies of a given Medium.
     * @param medium the medium the copies are of
     * @return A lit of all found copies, can be empty of none exist
     */
    ArrayList<Copy> findCopyByMedium(Medium medium);

    /**
     * returns all Books currently in the System.
     * @return an ArrayList of all books, can be empty if none exist yet
     */
    ArrayList<Book> getAllBooks();

    /**
     * returns all Discs currently in the System.
     * @return an ArrayList of all Discs, can be Empty if none exist yet
     */
    ArrayList<Disc> getAllDiscs();

    /**
     * returns all Copies a given user owns.
     * @param owner the owner whos copies are looked for
     * @return an ArrayList of all Copies the User listed as his own
     */
    ArrayList<Copy> findCopyByOwner(User owner);

    /**
     * returns all Mdeiums which contain the given String in their titel.
     * @param titel the titel(-part) someone is looking for
     * @return  an ArrayLsit of all matching Mediums
     */
    ArrayList<Medium> findMediumByTitel(String titel);

    /**
     * returns all Mediums that contain the given String in their Description.
     * @param desc a String that is part of the wanted Mediums description
     * @return a List of Mediums
     */
    ArrayList<Medium> findMediumByDescribtion(String desc);

    /**
     * finds all Copies at the given loction.
     * @param loc the location from which the searched copies should be from
     * @return an ArrayList of all matching copies
     */
    ArrayList<Copy> findCopyByLocation(String loc);

    /**
     * finds all Copies borrowed by a certain User.
     * @param borrower the borrowing user
     * @return An ArrayList of all Copies the User has borrowed
     */
    ArrayList<Copy> findCopyByBorrower(User borrower);



}
