package edu.hm.model;

/**
 * Created by Markus on 21.04.2017.
 */
public class Book extends Medium{

    private final String ISBN;

    public Book(String isbn, User owner, String titel, String description, String location){
        super(owner, titel, description, location);
        this.ISBN = isbn;
    }

    public String getISBN() {
        return ISBN;
    }

}