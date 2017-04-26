package edu.hm.Logic;

import edu.hm.REST.MediaAdminAccess;
import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumAdministartion implements MediaAdminAccess {

    MediumDataAccess mdata;

    public MediumAdministartion(MediumDataAccess dataAccess){
        mdata = dataAccess;
    }

    /**
     * create new book and test if all is correct
     */
    public String createBook(String isbn, String titel, String description,User curUser){
        String result = "something went wrong";
        if(checkValidISBN(isbn)){
            if(checkUserOK(curUser)){
            mdata.addMedium(isbn,titel, description);
            result = "OK";
        } else {result ="not Authorized";}}
        else{result= "invalid barcode";}

        return result;
    }

    public String createDisc(int barcode, String titel, String description, User curUser){
        String result = "something went wrong";
        if(checkValidBarcode(barcode)){
            if(checkUserOK(curUser)){
            mdata.addMedium(barcode, titel, description);
            result = "OK";
        } else {result ="not Authorized";}}
            else{result= "invalid barcode";}

        return result;
    }

    public String createCopy(User curUser, Medium medium, String location){
        String result = "something went wrong";
            if(checkUserOK(curUser)){
                mdata.addCopy(curUser, medium, location);
                result = "OK";
            } else {result ="not Authorized";}

        return result;
    }


    public Book findMediumByISBN(String isbn){
        Book result = null;
        if(mdata.getMediaList().size() > 0){
            for(Medium m: mdata.getMediaList()){
                if (m instanceof Book){
                    if (((Book) m).getISBN().equals(isbn))
                        result = (Book) m;
                }
            }
        }
        return result;
    }


    public Disc findMediumByBarcode(int barcode){
        Disc result = null;
        if(mdata.getMediaList().size() > 0){
            for(Medium m: mdata.getMediaList()){
                if (m instanceof Disc){
                    if (((Disc) m).getBARCODE() == barcode)
                        result = (Disc) m;
                }
            }
        }
        return result;
    }

    public ArrayList<Copy> findCopyByMedium(Medium medium){
        ArrayList<Copy> results = new ArrayList<> ();
        if(mdata.getCopyList().size()>0){
            for (Copy c: mdata.getCopyList()) {
                if(c.getMedium().equals(medium)){
                    results.add(c);
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


    private boolean checkValidISBN(String isbn){
        boolean isValid = false;

        if(isbn.length()==13){
            int checkSum = 0;
            for(int i = 0; i <13; i++){
                if(i%2 == 0){
                    checkSum += 3*((int) isbn.charAt(i));
                }
                else{
                    checkSum += (int) isbn.charAt(i);
                }
            }
            if(checkSum%10 == 0){
                isValid = true;
            }
        }

        return isValid;
    }
    private boolean checkValidBarcode(int barcode){return true;}


    /**
     * check if the user is ok.
     * @return
     */
    private boolean checkUserOK(User user){return user.isActivated();}

}
