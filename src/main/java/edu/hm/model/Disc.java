package edu.hm.model;

import javax.persistence.Entity;

/**
 * Created by Markus on 21.04.2017.
 */
@Entity
public class Disc extends Medium {

    private String barcode;
    private String director;
    private int fsk;

    /**
     * ctor for new discs.
     * @param barcode the barcode for the new disc.
     * @param titel the title of the new Disc
     * @param director the director of the discs contetnts
     * @param fsk the discs fsk
     * @param description a description of the discs contents
     */
    public Disc(String barcode, String titel, String director, int fsk, String description) {
        super(titel, description);
        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    /**
     * default ctor for hibernate.
     */
    public Disc() {
    }

    /**
     * getter for the Discs barcode.
     * @return a string containing the discs barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * setter for the barcode for hibernate.
     * @param barcode the barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * getter for the discs Direktor.
     * @return a String containign teh Diretor information
     */
    public String getDirector() {
        return director;
    }

    /**
     * setter for the Director.
     * @param director Strign of the new Director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * getter for the fsk.
     * @return an int representing the fsk
     */
    public int getFsk() {
        return fsk;
    }

    /**
     * setter for the discs fsk.
     * @param fsk the disks new fsk
     */
    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqual = false;
        if (other != null) {
            if (other instanceof Disc) {
                isEqual = ((Disc) other).getBarcode().equals(this.getBarcode());
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getBarcode() != null ? getBarcode().hashCode() : 0);
        result = 31 * result + (getDirector() != null ? getDirector().hashCode() : 0);
        result = 31 * result + getFsk();
        return result;
    }
}