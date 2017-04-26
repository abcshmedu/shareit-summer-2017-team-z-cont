package edu.hm.model;

/**
 * Created by Markus on 21.04.2017.
 */
public class Disc extends Medium{

    private final int BARCODE;

    public Disc(int barcode,String titel, String description){
        super(titel, description);
        this.BARCODE = barcode;
    }

    public int getBARCODE() {
        return BARCODE;
    }
}