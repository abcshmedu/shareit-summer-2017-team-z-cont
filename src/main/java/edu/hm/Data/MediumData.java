package edu.hm.Data;

import edu.hm.Logic.MediumDataAccess;
import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumData implements MediumDataAccess {

    private ArrayList<Medium> mediaList;
    private ArrayList<Copy> copies;


    /**
     * creates a new MediumData ewith an empty MediaList.
     */
    public MediumData(){
        mediaList = new ArrayList<>();
        copies = new ArrayList<>();
    }

    /**
     * creates a new MediumData with a pre existing MediaList.
     * @param mediaCatalogue an ArrayList Containing all Mediums the MediumData should know from the start
     */
    public MediumData(ArrayList<Medium> mediaCatalogue){

        mediaList = mediaCatalogue;
    }

    @Override
    public String addDisc(String barcode, String titel, String director, int fsk, String description) {
        String answer = "exists already";
        Disc newDisc = new Disc(barcode, titel, director, fsk, description);
        boolean exists = false;
        for (Medium m : mediaList) {
            if (m.equals(newDisc)){
                exists = true;
            }
        }
        if (!exists) {
            mediaList.add(newDisc);
            answer = "OK";
        }
        return answer;
    }

    @Override
    public Copy addCopy(User owner, Medium medium, String location) {
        Copy newCopy = new Copy(owner, medium, location);
        copies.add(newCopy);
        return newCopy;
    }

    @Override
    public String addBook(String isbn, String titel, String author, String description) {
        String answer = "exists already";
        Book newBook = new Book(isbn, titel, author, description);
        boolean exists = false;
        for (Medium m : mediaList) {
            if (m.equals(newBook)){
                exists = true;
            }
        }
        if(!exists) {
            mediaList.add(newBook);
            answer = "OK";
        }
        return answer;
    }



    @Override
    public ArrayList<Medium> getMediaList() {
        return mediaList;
    }

    @Override
    public ArrayList<Copy> getCopyList() {
        return copies;
    }


    @Override
    public String toString() {
        // please also add a toString on a Medium
        return
                "mediaList=" + mediaList +
                '}';
    }
}
