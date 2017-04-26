package edu.hm.Logic;

import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediumDataAccess {

    Book addMedium(String isbn, String titel, String description);
    Disc addMedium(int barcode, String titel, String description);
    Copy addCopy(User owner, Medium medium, String location);
    ArrayList<Medium> getMediaList();
    ArrayList<Copy> getCopyList();

}
