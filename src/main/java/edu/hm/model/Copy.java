package edu.hm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Maximilian on 26.04.2017.
 */
@Entity
public class Copy {
    private Medium medium;
    private User owner;
    private String location;
    private boolean isBorrowed;
    private User borrowedBy;
    private String id;

    /**
     * ctor for new copies.
     * @param owner the User that owns the new copy
     * @param medium the medium the copy is of
     * @param location where the copy can be borrowed
     */
    public Copy(User owner, Medium medium, String location) {
        this.owner = owner;
        this.medium = medium;
        this.location = location;
        isBorrowed = false;
        borrowedBy = null;
    }

    /**
     * default ctor for hibernate.
     */
    public Copy() {
    }

    /**
     * borrows a copy.
     * @param borrower the user that wants to borrow the copy
     * @return boolean true if it worked, false if not
     */
    public boolean borrowCopy(User borrower) {
        boolean worked = false;
        if (!isBorrowed) {
            isBorrowed = true;
            borrowedBy = borrower;
            worked = true; }
        return worked;
    }

    /**
     * method used to return a copy.
     * @param returner the user that wants to return the copy
     * @return a boolean that represents if the operation worked or not
     */
    public boolean returnCopy(User returner) {
        boolean worked = false;

        if (isBorrowed) {
            if (returner == borrowedBy) {
                borrowedBy = null;
                isBorrowed = false;
                worked = true;
            }
        }
        return worked;
    }

    /**
     * allows a Owner to change the location of his copy.
     * @param owner the User that tries to change the location, needs to be the owner
     * @param newLocation the new Location of the copy
     * @return a boolean representign if this operation worked or not
     */
    public boolean moveCopy(User owner, String newLocation) {
        boolean worked = false;
        if (this.owner == owner) {
            location = newLocation;
            worked = true;
        }
        return worked;
    }

    /**
     * getter for the Copies medium.
     * @return the medium this object represents a copy of
     */
    @ManyToOne
    public Medium getMedium() {
        return medium;
    }

    /**
     * setter for the Medium.
     * @param medium the new Medium this copy represents
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     * getter for the copies owner.
     * @return the User that owns this copy
     */
    @ManyToOne
    public User getOwner() {
        return owner;
    }

    /**
     * Setter for the Owner.
     * @param owner the new Owner of this copy
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * getter for the Copies Location.
     * @return a Strign representation of the copies location
     */
    public String getLocation() {
        return location;
    }

    /**
     * setter for the copies location.
     * @param location the new Location of this copy
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * getter for the boorowed status of the copy.
     * @return a boolean that tells if this copy is lready borrowed or not
     */
    public boolean isBorrowed() {
        return isBorrowed;
    }

    /**
     * setter for the isBorrowed boolean.
     * @param borrowed the new state of the copie (true = is borrrowed)
     */
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    /**
     * getter for the user that borrowed this Copy.
     * @return the User that is currently borrowing this copy
     */
    @ManyToOne
    public User getBorrowedBy() {
        return borrowedBy;
    }

    /**
     * setter for the current borrower.
     * @param borrowedBy the new User borrowing this copy
     */
    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Copy copy = (Copy) o;

        if (isBorrowed() != copy.isBorrowed()) {
            return false;
        }
        if (!getMedium().equals(copy.getMedium())) {
            return false;
        }
        if (!getOwner().equals(copy.getOwner())) {
            return false;
        }
        if (getLocation() != null ? !getLocation().equals(copy.getLocation()) : copy.getLocation() != null) {
            return false;
        }
        return getBorrowedBy() != null ? getBorrowedBy().equals(copy.getBorrowedBy()) : copy.getBorrowedBy() == null;

    }

    @Override
    public int hashCode() {
        int result = getMedium() != null ? getMedium().hashCode() : 0;
        result = 31 * result
                + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result
                + (getLocation() != null ? getLocation().hashCode() : 0);
        result = 31 * result
                + (isBorrowed() ? 1 : 0);
        result = 31 * result
                + (getBorrowedBy() != null ? getBorrowedBy().hashCode() : 0);
        return result;
    }



    /**
     * getter for the hibernate Id.
     * @return the Id as a String
     */
    @Id
    public String getId() {
        return id;
    }

    /**
     * setter for the hibernate id.
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }
}
