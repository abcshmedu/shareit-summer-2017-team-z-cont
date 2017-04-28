package edu.hm.Logic;

import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediumDataAccess {

    String addMedium(String isbn, String titel, String author, String description);
    String addDisc(String barcode, String titel, String description);
    Copy addCopy(User owner, Medium medium, String location);
    ArrayList<Medium> getMediaList();
    ArrayList<Copy> getCopyList();

}
