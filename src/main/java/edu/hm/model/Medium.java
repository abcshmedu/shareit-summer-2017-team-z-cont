package edu.hm.model;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class Medium {

    String location;
    boolean isBorrowed;
    User borrowedBy;
    String titel;
    String description;
    User owner;

    public  Medium(){

    }
    public Medium(User owner, String titel, String description, String location){
        this.owner = owner;
        this.titel = titel;
        this.description = description;
        this.location = location;
        isBorrowed = false;
    }

    public boolean borrowMedium(User borrower){
        boolean worked = false;
        if(isBorrowed == false){
        isBorrowed = true;
        borrowedBy = borrower;
        worked = true; }
        return worked;
    }

    public boolean returnMedium(User returner){
        boolean worked = false;

         if(isBorrowed){
             if(returner==borrowedBy){
                 borrowedBy = null;
                 isBorrowed = false;
                 worked = true;
             }
         }
        return worked;
    }

    public boolean moveMedium(User owner, String newLocation){
        boolean worked = false;
        if(this.owner==owner){
            location = newLocation;
            worked = true;
        }
        return worked;
    }

    public String getLocation(){
        return location;
    }

    public User getOwner(){
        return owner;
    }

    public String getTitel(){
        return titel;
    }

    public String getDescription(){
        return description;
    }

    public boolean getIsBorrowed(){
        return isBorrowed;
    }
    public User getBorrowedBy(){
        return borrowedBy;
    }
}
