package edu.hm.Data;

import edu.hm.Logic.MediumDataAccess;
import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.Medium;
import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumData implements MediumDataAccess {

    // we need the same list over all instances.
    private ArrayList<Medium> mediaList;


    public MediumData(){
        mediaList = new ArrayList<>();
    }

    public MediumData(ArrayList<Medium> mediaCatalogue){
        mediaList=mediaCatalogue;
    }

    public Disc addMedium(int barcode, User owner, String titel, String description, String location) {
        Disc newDisc = new Disc(barcode, owner, titel, description, location);
        mediaList.add(newDisc);
        return newDisc;
    }

    public Book addMedium(String isbn, User owner, String titel, String description, String location) {
        Book newBook = new Book(isbn, owner, titel, description, location);
        mediaList.add(newBook);
        return newBook;
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
