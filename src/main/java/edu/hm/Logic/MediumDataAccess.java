package edu.hm.Logic;

import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.Medium;
import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediumDataAccess {

    Book addMedium(String isbn, User owner, String titel, String description, String location);
    Disc addMedium(int barcode, User owner, String titel, String description, String location);
    ArrayList<Medium> getMediaList();

}
