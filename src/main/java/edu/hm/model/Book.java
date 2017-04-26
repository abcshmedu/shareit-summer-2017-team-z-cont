package edu.hm.model;

/**
 * Created by Markus on 21.04.2017.
 */
public class Book extends Medium{

    private final String ISBN;

    public Book(String isbn,String titel, String description){
        super(titel, description);
        this.ISBN = isbn;
    }

    public String getISBN() {
        return ISBN;
    }

}