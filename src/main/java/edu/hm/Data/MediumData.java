package edu.hm.Data;

import edu.hm.Logic.MediumDataAccess;
import edu.hm.model.Medium;
import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumData implements MediumDataAccess {

    // we need the same list over all instances.
    private ArrayList<Medium> mediaList = new ArrayList<>();


    public MediumData(){
        // we need the same list over all instances.
        //mediaList = new ArrayList<>();
    }

    public MediumData(ArrayList<Medium> mediaCatalogue){
        mediaList=mediaCatalogue;
    }

    public void addMedium(User owner, String titel, String description, String Location) {
        mediaList.add(new Medium(owner, titel, description, Location));
    }


    public ArrayList<Medium> findMediumByOwner(User owner){
       ArrayList results = new ArrayList<Medium> ();
        if(mediaList.size()>0){
            for (Medium m: mediaList) {
                if(m.getOwner()!=null&&m.getOwner() == owner){
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
                if(m.getTitel()!=null&&m.getTitel().contains(titel)){
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
                if(m.getDescription()!=null&&m.getDescription().contains(desc)){
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
                if(m.getLocation()!=null&&m.getLocation().contains(loc)){
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
                if(m.getBorrowedBy()!=null&&m.getBorrowedBy()==borrower){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> getMediaList() {
        return mediaList;
    }

    @Override
    public String toString() {
        // please also add a toString on a Medium
        return
                "mediaList=" + mediaList +
                '}';
    }
}
