package edu.hm.Logic;

import edu.hm.model.Medium;
import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public interface MediumDataAccess {

    void addMedium(User owner, String titel, String description, String Location);
    ArrayList<Medium> findMediumByOwner(User owner);
    ArrayList<Medium> findMediumByTitel(String titel);
    ArrayList<Medium> findMediumByDescribtion(String desc);
    ArrayList<Medium> findMediumByLocation(String loc);
    ArrayList<Medium> findMediumByBorrower(User borrower);

}
