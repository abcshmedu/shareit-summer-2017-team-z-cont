package edu.hm.model;

/**
 * Created by Markus on 21.04.2017.
 */
public class Book extends Medium{

    private final String ISBN;
    private String author;

    public Book(String isbn,String titel, String author, String description){
        super(titel, description);
        this.ISBN = isbn;
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object other){
        boolean isEqual = false;
        if(other!=null){
            if(other instanceof Book){
                isEqual =((Book) other).getISBN().equals(this.getISBN());
            }
        }
            return isEqual;
    }


}