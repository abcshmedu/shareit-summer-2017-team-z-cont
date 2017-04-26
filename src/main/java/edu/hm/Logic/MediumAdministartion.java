package edu.hm.Logic;

import edu.hm.model.Book;
import edu.hm.model.Medium;
import edu.hm.Data.MediumData;
import edu.hm.model.User;

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



    public boolean checkValidISBN(String isbn){return true;}
    public boolean checkValidBarcode(){return true;}

    /**
     * check if the user is ok.
     * @return
     */
    public boolean checkUserOK(){return true;}


}
