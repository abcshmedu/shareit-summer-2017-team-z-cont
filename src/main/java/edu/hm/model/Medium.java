package edu.hm.model;

/**
 * Created by Maximilian on 21.04.2017.
 */
public abstract class Medium {

    private String titel;
    private String description;

    public  Medium(){

    }
    public Medium(String titel, String description){
        this.titel = titel;
        this.description = description;
    }

    public String getTitel(){
        return titel;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString() {
        return "Medium: {" +
                "\'titel\':'" + titel + '\'' +
                ", \'description\':'" + description +
                "\'}";
    }

    @Override
    public boolean equals(Object other){
        boolean isEqual = false;
        if(other!=null) {
            if(other instanceof Medium){
                isEqual = other.toString().equals(this.toString());
            }
        }
        return isEqual;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
