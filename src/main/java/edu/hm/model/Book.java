package edu.hm.model;

import javax.persistence.Entity;

/**
 * Created by Markus on 21.04.2017.
 */
@Entity
public class Book extends Medium {

    private String isbn;
    private String author;

    /**
     * ctor for new books.
     * @param isbn the new books isbn
     * @param titel the new books title
     * @param author the new books author
     * @param description the new books description
     */
    public Book(String isbn, String titel, String author, String description) {
        super(titel, description);
        this.isbn = isbn;
        this.author = author;
    }

    /**
     * default ctor for hibernate.
     */
    public Book() {
    }


    /**
     * getter for the books isbn.
     * @return the books isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * setter for the isbn.
     * @param isbn the books isbn
     */
    public void setIsbn(String isbn) {
        if (getIsbn() == null) {
            this.isbn = isbn;
        }
    }
    /**
     * getter for the books author.
     * @return the books author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * setter for the Author.
     * @param author the books new Author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqual = false;
        if (other != null) {
            if (other instanceof Book) {
                isEqual = ((Book) other).getIsbn().equals(this.getIsbn());
            }
        }
            return isEqual;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        return result;
    }
}