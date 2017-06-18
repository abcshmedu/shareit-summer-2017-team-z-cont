package edu.hm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Maximilian on 21.04.2017.
 */
@Entity
public abstract class Medium {

    private int id;
    private String titel;
    private String description;

    /**
     * default ctor.
     */
    public  Medium() {

    }

    /**
     * ctor for a medium with titel and description.
     * @param titel the mediums titel
     * @param description the mediums description
     */
    public Medium(String titel, String description) {
        this.titel = titel;
        this.description = description;
    }

    /**
     * returns the Mediums Titel.
     * @return a String representing the Mediums titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     * returns the Mediums Description.
     * @return a Strign representign the Mediums description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Medium: {"
                + "\'titel\':'"
                + titel
                + '\''
                + ", \'description\':'"
                + description
                + "\'}";
    }

    @Override
    public boolean equals(Object other) {
        boolean isEqual = false;
        if (other != null) {
            if (other instanceof Medium) {
                isEqual = other.toString().equals(this.toString());
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        int result = getTitel() != null ? getTitel().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }

    /**
     * setter for the title.
     * @param titel the mediums new title
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     * setter for the MEdiums Description.
     * @param description the medims new Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * getter for the Hibernate id.
     * @return the ID
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * setter for the hibernate id.
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }
}
