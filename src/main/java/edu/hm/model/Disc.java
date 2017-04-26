package edu.hm.model;

/**
 * Created by Markus on 21.04.2017.
 */
public class Disc extends Medium{

    private final int BARCODE;

    public Disc(int barcode, User owner, String titel, String description, String location){
        super(owner, titel, description, location);
        this.BARCODE = barcode;
    }

    public int getBARCODE() {
        return BARCODE;
    }
}