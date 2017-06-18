package edu.hm.Logic;

import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediumDataAccess {

    /**
     * adds abook to the list of Mediums.
     * @param isbn the books isbn
     * @param titel the books titel
     * @param author the books author
     * @param description the books description
     * @return A string response OK if it worke dor an error mesage
     */
    String addBook(String isbn, String titel, String author, String description);

    /**
     * Adds a Disc to the list of Mediums.
     * @param barcode the discs Barcode
     * @param titel the Discs title
     * @param director the discs director
     * @param fsk the discs fsk
     * @param description the discs description
     * @return A String response OK if it worked of an error message
     */
    String addDisc(String barcode, String titel, String director, int fsk, String description);

    /**
     * adds A Copy to the List of copies.
     * @param owner  the owner of the copy
     * @param medium the medium the copy is of
     * @param location where the Copy can eb found
     * @return the created Copy
     */
    Copy addCopy(User owner, Medium medium, String location);

    /**
     * Returns the List of all Mediums.
     * @return a List containing all mediums the Data Layer contains at the moment
     */
    ArrayList<Medium> getMediaList();

    /**
     * retuns a List of all Copies the Data Layer has at the moment.
     * @return an ArrayList containing all known copies
     */
    ArrayList<Copy> getCopyList();

    /**
     * signals the dataAccess to save all Changes.
     */
    void saveChanges();


}
