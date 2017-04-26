package edu.hm.Logic;

import edu.hm.model.Book;
import edu.hm.model.Disc;
import edu.hm.model.Medium;
import edu.hm.Data.MediumData;
import edu.hm.model.User;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumAdministartion {

    MediumData mdata = new MediumData();

    /**
     * create new book and test if all is correct
     */
    public String createBook(String isbn, User owner, String titel, String description, String location){
        String result = "something went wrong";
        if(checkValidISBN(isbn) && checkUserOK()){
            mdata.addMedium(new Book(isbn, owner, titel, description, location));
            result = "OK";
        }

        return result;
    }


    public ArrayList<Book> findMediumByISBN(String isbn){
        ArrayList results = new ArrayList<Medium> ();
        if(mdata.getMediaList().size() > 0){
            for(Medium m: mdata.getMediaList()){
                if (m instanceof Book){
                    if (((Book) m).getISBN().equals(isbn))
                        results.add(m);
                }
            }
        }
        return results;
    }


    public ArrayList<Disc> findMediumByBarcode(int barcode){
        ArrayList results = new ArrayList<Disc> ();
        if(mdata.getMediaList().size() > 0){
            for(Medium m: mdata.getMediaList()){
                if (m instanceof Disc){
                    if (((Disc) m).getBARCODE() == barcode)
                        results.add((Disc) m);
                }
            }
        }
        return results;
    }

    public ArrayList<Book> getAllBooks(){
        ArrayList results = new ArrayList<Medium> ();
        if(mdata.getMediaList().size() > 0){
            for(Medium m: mdata.getMediaList()){
                if (m instanceof Book)
                        results.add((Book) m);
                }
            }
        return results;
    }

    public ArrayList<Disc> getAllDiscs(){
        ArrayList results = new ArrayList<Disc> ();
        if(mdata.getMediaList().size() > 0){
            for(Medium m: mdata.getMediaList()){
                if (m instanceof Disc)
                    results.add((Disc) m);
            }
        }
        return results;
    }



    public ArrayList<Medium> findMediumByOwner(User owner){
        ArrayList results = new ArrayList<Medium> ();
        if(mdata.getMediaList().size()>0){
            for (Medium m: mdata.getMediaList()) {
                if(m.getOwner()!=null&&m.getOwner() == owner){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByTitel(String titel){
        ArrayList results = new ArrayList<Medium> ();
        if(mdata.getMediaList().size()>0){
            for (Medium m: mdata.getMediaList()) {
                if(m.getTitel()!=null&&m.getTitel().contains(titel)){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByDescribtion(String desc){
        ArrayList results = new ArrayList<Medium> ();
        if(mdata.getMediaList().size()>0){
            for (Medium m: mdata.getMediaList()) {
                if(m.getDescription()!=null&&m.getDescription().contains(desc)){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByLocation(String loc){
        ArrayList results = new ArrayList<Medium> ();
        if(mdata.getMediaList().size()>0){
            for (Medium m: mdata.getMediaList()) {
                if(m.getLocation()!=null&&m.getLocation().contains(loc)){
                    results.add(m);
                }
            }
        }

        return results;
    }

    public ArrayList<Medium> findMediumByBorrower(User borrower){
        ArrayList results = new ArrayList<Medium> ();
        if(mdata.getMediaList().size()>0){
            for (Medium m: mdata.getMediaList()) {
                if(m.getBorrowedBy()!=null&&m.getBorrowedBy()==borrower){
                    results.add(m);
                }
            }
        }

        return results;
    }


    public boolean checkValidISBN(String isbn){return true;}
    public boolean checkValidBarcode(){return true;}

    /**
     * check if the user is ok.
     * @return
     */
    public boolean checkUserOK(){return true;}


}
