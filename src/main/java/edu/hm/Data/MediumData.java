package edu.hm.Data;

import edu.hm.Logic.MediumDataAccess;
import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumData implements MediumDataAccess {

    // we need the same list over all instances.
    private ArrayList<Medium> mediaList;
    private ArrayList<Copy> copies;


    public MediumData(){
        mediaList = new ArrayList<>();
        copies = new ArrayList<>();
    }

    public MediumData(ArrayList<Medium> mediaCatalogue){
        mediaList=mediaCatalogue;
    }

    public Disc addMedium(int barcode, String titel, String description) {
        Disc newDisc = new Disc(barcode, titel, description);
        mediaList.add(newDisc);
        return newDisc;
    }

    @Override
    public Copy addCopy(User owner, Medium medium, String location) {
        Copy newCopy = new Copy(owner, medium, location);
        copies.add(newCopy);
        return newCopy;
    }

    public Book addMedium(String isbn, String titel, String description) {
        Book newBook = new Book(isbn, titel, description);
        mediaList.add(newBook);
        return newBook;
    }



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
