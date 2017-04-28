package edu.hm.model;

/**
 * Created by Maximilian on 26.04.2017.
 */
public class Copy {
    private Medium medium;
    private User owner;
    private String location;
    private boolean isBorrowed;
    private User borrowedBy;

    public Copy(User owner, Medium medium, String location){
        this.owner = owner;
        this.medium = medium;
        this.location = location;
        isBorrowed = false;
        borrowedBy = null;
    }

    public boolean borrowCopy(User borrower){
        boolean worked = false;
        if(isBorrowed == false){
            isBorrowed = true;
            borrowedBy = borrower;
            worked = true; }
        return worked;
    }

    public boolean returnCopy(User returner){
        boolean worked = false;

        if(isBorrowed){
            if(returner==borrowedBy){
                borrowedBy = null;
                isBorrowed = false;
                worked = true;
            }
        }
        return worked;
    }

    public boolean moveCopy(User owner, String newLocation){
        boolean worked = false;
        if(this.owner==owner){
            location = newLocation;
            worked = true;
        }
        return worked;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(User borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Copy copy = (Copy) o;

        if (isBorrowed() != copy.isBorrowed()) return false;
        if (!getMedium().equals(copy.getMedium())) return false;
        if (!getOwner().equals(copy.getOwner())) return false;
        if (getLocation() != null ? !getLocation().equals(copy.getLocation()) : copy.getLocation() != null)
            return false;
        return getBorrowedBy() != null ? getBorrowedBy().equals(copy.getBorrowedBy()) : copy.getBorrowedBy() == null;

    }
}
