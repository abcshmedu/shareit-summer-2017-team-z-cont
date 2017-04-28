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
    public String createBook(String isbn, String titel, String author, String description,User curUser){
        String result = "something went wrong";
        if(checkValidISBN(isbn)){
            if(checkUserOK(curUser)){
            result = mdata.addMedium(isbn,titel, author, description);
        } else {result ="not Authorized";}}
        else{result= "invalid isbn";}
        return result;
    }

    public String createDisc(String barcode, String titel, String description, User curUser){
        String result = "something went wrong";
        if(checkValidBarcode(barcode)){
            if(checkUserOK(curUser)){
            result = mdata.addDisc(barcode, titel, description);
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


    public Disc findMediumByBarcode(String barcode){
        Disc result = null;
        if(mdata.getMediaList().size() > 0){
            for(Medium m: mdata.getMediaList()){
                if (m instanceof Disc){
                    if (((Disc) m).getBARCODE().equals(barcode))
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

    public String editBook(String isbn, String titel, String author, String description,User curUser){
        String result = "OK";
        if(checkUserOK(curUser)) {
            Book toBeEdited = findMediumByISBN(isbn);
            if (toBeEdited != null) {
                if (titel != null) {
                    toBeEdited.setTitel(titel);
                } else {
                    result = "no Titel";
                }
                if (author != null) {
                    toBeEdited.setAuthor(author);
                } else {
                    result = "no Author";
                }
                if (description != null) {
                    toBeEdited.setDescription(description);
                }
            }
        }
        return result;
    }

    public String editDisc(String barcode, String titel, String description,User curUser){
        String result = "OK";
        if(checkUserOK(curUser)) {
            Disc toBeEdited = findMediumByBarcode(barcode);
            if (toBeEdited != null) {
                if (titel != null) {
                    toBeEdited.setTitel(titel);
                } else {
                    result = "no Titel";
                }
                if (description != null) {
                    toBeEdited.setDescription(description);
                }
            }
        }
        return result;
    }


    private boolean checkValidISBN(String isbn) {
        if(isbn.length()==13){
        return isbn.charAt(12) == isbn13CheckDigit(isbn);
        }
        else{
            return false;
        }
    }
    private boolean checkValidBarcode(String barcode) {
        if (barcode.length() == 13) {
            return barcode.charAt(12) == isbn13CheckDigit(barcode);
        } else {
            return false;
        }
    }

    /**
     * check if the user is ok.
     * @return
     */
    private boolean checkUserOK(User user){return user.isActivated();}


    private char isbn13CheckDigit(String str) {
        // Sum of the 12 digits.
        int sum = 0;
        // Digits counted.
        int digits = 0;
        // Start multiplier at 1. Alternates between 1 and 3.
        int multiplier = 1;
        // Treat just the 1st 12 digits of the string.
        for (int i = 0; i < str.length() && digits < 12; i++) {
            // Pull out that character.
            char c = str.charAt(i);
            // Is it a digit?
            if ('0' <= c && c <= '9') {
                // Keep the sum.
                sum += multiplier * (c - '0');
                // Flip multiplier between 1 and 3 by flipping the 2^1 bit.
                multiplier ^= 2;
                // Count the digits.
                digits += 1;
            }
        }
        // What is the check digit?
        int checkDigit = (10 - (sum % 10)) % 10;
        // Give it back to them in character form.
        return (char) (checkDigit + '0');
    }

}
