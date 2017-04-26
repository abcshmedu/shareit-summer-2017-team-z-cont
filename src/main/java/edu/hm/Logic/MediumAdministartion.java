package edu.hm.Logic;

import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumAdministartion {

    MediumDataAccess mdata;

    public MediumAdministartion(MediumDataAccess dataAccess){
        mdata = dataAccess;
    }

    /**
     * create new book and test if all is correct
     */
    public String createBook(String isbn, User owner, String titel, String description, String location){
        String result = "something went wrong";
        if(checkValidISBN(isbn)){
            if(checkUserOK(owner)){
            mdata.addMedium(isbn,titel, description);
            result = "OK";
        } else {result ="not Authorized";}}
        else{result= "invalid barcode";}

        return result;
    }

    public String createDisc(int barcode, User owner, String titel, String description, String location){
        String result = "something went wrong";
        if(checkValidBarcode(barcode)){
            if(checkUserOK(owner)){
            mdata.addMedium(barcode, titel, description);
            result = "OK";
        } else {result ="not Authorized";}}
            else{result= "invalid barcode";}

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



    public ArrayList<Copy> findCopyByOwner(User owner){
        ArrayList<Copy> results = new ArrayList<> ();
        if(mdata.getCopyList().size()>0){
            for (Copy c: mdata.getCopyList()) {
                if(c.getOwner()!=null&&c.getOwner() == owner){
                    results.add(c);
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

    public ArrayList<Copy> findCopyByLocation(String loc){
        ArrayList<Copy> results = new ArrayList<> ();
        if(mdata.getCopyList().size()>0){
            for (Copy c: mdata.getCopyList()) {
                if(c.getLocation()!=null&&c.getLocation().contains(loc)){
                    results.add(c);
                }
            }
        }

        return results;
    }

    public ArrayList<Copy> findCopyByBorrower(User borrower){
        ArrayList<Copy> results = new ArrayList<> ();
        if(mdata.getCopyList().size()>0){
            for (Copy c: mdata.getCopyList()) {
                if(c.getBorrowedBy()!=null&&c.getBorrowedBy()==borrower){
                    results.add(c);
                }
            }
        }

        return results;
    }


    public boolean checkValidISBN(String isbn){return true;}
    public boolean checkValidBarcode(int barcode){return true;}

    /**
     * check if the user is ok.
     * @return
     */
    public boolean checkUserOK(User user){return true;}


}
