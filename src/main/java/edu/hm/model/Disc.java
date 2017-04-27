package edu.hm.model;

/**
 * Created by Markus on 21.04.2017.
 */
public class Disc extends Medium{

    private final String BARCODE;

    public Disc(String barcode,String titel, String description){
        super(titel, description);
        this.BARCODE = barcode;
    }

    public String getBARCODE() {
        return BARCODE;
    }

    @Override
    public boolean equals(Object other){
        boolean isEqual = false;
        if(other!=null){
            if(other instanceof Disc){
                isEqual =((Disc) other).getBARCODE().equals(this.getBARCODE());
            }
        }
        return isEqual;
    }
}