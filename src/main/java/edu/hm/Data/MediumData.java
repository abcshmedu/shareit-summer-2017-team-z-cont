package edu.hm.Data;

import edu.hm.Logic.MediumDataAccess;
import edu.hm.model.Medium;
import edu.hm.model.User;
import org.w3c.dom.stylesheets.MediaList;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumData implements MediumDataAccess {


    private ArrayList<Medium> mediaList;


    public void addMedium(User owner, String titel, String description, String Location) {
        mediaList.add(new Medium(owner, titel, description, Location));
    }

    public ArrayList<Medium> findMediumByOwner(User owner){
       ArrayList results = new ArrayList<Medium> ();
        if(mediaList.size()>0){
            for (Medium m: mediaList) {
                if(m.getOwner()== owner){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByTitel(String titel){
        ArrayList results = new ArrayList<Medium> ();
        if(mediaList.size()>0){
            for (Medium m: mediaList) {
                if(m.getTitel().contains(titel)){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByDescribtion(String desc){
        ArrayList results = new ArrayList<Medium> ();
        if(mediaList.size()>0){
            for (Medium m: mediaList) {
                if(m.getDescription().contains(desc)){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByLocation(String loc){
        ArrayList results = new ArrayList<Medium> ();
        if(mediaList.size()>0){
            for (Medium m: mediaList) {
                if(m.getLocation().contains(loc)){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByBorrower(User borrower){
        ArrayList results = new ArrayList<Medium> ();
        if(mediaList.size()>0){
            for (Medium m: mediaList) {
                if(m.getBorrowedBy()==borrower){
                    results.add(m);
                }
            }
        }

        return results;
    }

}
