package edu.hm.model;

/**
 * Created by Markus on 21.04.2017.
 */
public class Disc extends Medium{

    private final String BARCODE;
    private String director;
    private int fsk;

    public Disc(String barcode,String titel, String director, int fsk, String description){
        super(titel, description);
        this.BARCODE = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    public String getBARCODE() {
        return BARCODE;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getFsk() {
        return fsk;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
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