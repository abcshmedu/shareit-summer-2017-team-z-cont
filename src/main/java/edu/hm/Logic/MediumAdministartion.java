package edu.hm.Logic;

import com.google.inject.Inject;
import edu.hm.REST.MediaAdminAccess;
import edu.hm.model.*;

import java.util.ArrayList;

/**
 * Created by Maximilian on 21.04.2017.
 */
public class MediumAdministartion implements MediaAdminAccess {
    private static final int ISBN_LENGTH = 13;
    private static final int TEN = 10;
    /**
     * Data-Layer access.
     */
    @Inject
    private MediumDataAccess mdata;

    @Inject
    private UserAdministartion userValidation;

    /**
     * c-tor with a given data-Layer access.
     * @param dataAccess the DataLayer Object that will be used through the interface Medium DataAccess.
     * @param authorization the UserAdministration used to validate incoming usertokens
     */
    public MediumAdministartion(MediumDataAccess dataAccess, UserAdministartion authorization) {
        mdata = dataAccess;
        userValidation = authorization;
    }

    @Override
    public String createBook(String isbn, String titel, String author, String description, String token) {
        isbn = isbn.replaceAll("-", "");
        String result = "something went wrong";

        System.out.print("\n\n" + token);
        User curUser = userValidation.getUserForToken(token);
        System.out.print("\n\n" + curUser);
        if (checkValidISBN(isbn)) {
            if (curUser != null && checkUserOK(curUser)) {
            result = mdata.addBook(isbn, titel, author, description);
                } else {
                    result = "not Authorized";
                }
        }
        else {
            result = "invalid isbn";
        }
        return result;
    }

    @Override
    public String createDisc(String barcode, String titel, String director, int fsk, String description, String token) {
        barcode = barcode.replaceAll("-", "");
        String result = "something went wrong";
        User curUser = userValidation.getUserForToken(token);
        if (checkValidBarcode(barcode)) {
            if (checkUserOK(curUser)) {
            result = mdata.addDisc(barcode, titel, director, fsk, description);
        } else {
                result = "not Authorized";
            }
        }
            else {
            result = "invalid barcode";
        }
        return result;
    }

    @Override
    public String createCopy(String token, Medium medium, String location) {
        String result = "something went wrong";
        User curUser = userValidation.getUserForToken(token);
            if (checkUserOK(curUser)) {
                mdata.addCopy(curUser, medium, location);
                result = "OK";
            } else {
                result = "not Authorized";
            }
        return result;
    }

  @Override
    public Book findMediumByISBN(String isbn) {
      isbn = isbn.replaceAll("-", "");
        Book result = null;
        if (mdata.getMediaList().size() > 0) {
            for (Medium m: mdata.getMediaList()) {
                if (m instanceof Book) {
                    if (((Book) m).getIsbn().equals(isbn)) {
                        result = (Book) m;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Disc findMediumByBarcode(String barcode) {
        barcode = barcode.replaceAll("-", "");
        Disc result = null;
        if (mdata.getMediaList().size() > 0) {
            for (Medium m: mdata.getMediaList()) {
                if (m instanceof Disc) {
                    if (((Disc) m).getBarcode().equals(barcode)) {
                        result = (Disc) m;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<Copy> findCopyByMedium(Medium medium) {
        ArrayList<Copy> results = new ArrayList<>();
        if (mdata.getCopyList().size() > 0) {
            for (Copy c: mdata.getCopyList()) {
                if (c.getMedium().equals(medium)) {
                    results.add(c);
                }
            }
        }

        return results;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        ArrayList results = new ArrayList<Medium>();
        if (mdata.getMediaList().size() > 0) {
            for (Medium m: mdata.getMediaList()) {
                if (m instanceof Book) {
                    results.add((Book) m);
                }
                }
            }
        return results;
    }

    @Override
    public ArrayList<Disc> getAllDiscs() {
        ArrayList results = new ArrayList<Disc>();
        if (mdata.getMediaList().size() > 0) {
            for (Medium m: mdata.getMediaList()) {
                if (m instanceof Disc) {
                    results.add((Disc) m);
                }
            }
        }
        return results;
    }


    @Override
    public ArrayList<Copy> findCopyByOwner(User owner) {
        ArrayList<Copy> results = new ArrayList<>();
        if (mdata.getCopyList().size() > 0) {
            for (Copy c: mdata.getCopyList()) {
                if (c.getOwner() != null && c.getOwner().getUsername().equals(owner.getUsername())) {
                    results.add(c);
                }
            }
        }

        return results;
    }

    @Override
    public ArrayList<Medium> findMediumByTitel(String titel) {
        ArrayList results = new ArrayList<Medium>();
        if (mdata.getMediaList().size() > 0) {
            for (Medium m: mdata.getMediaList()) {
                if (m.getTitel() != null && m.getTitel().contains(titel)) {
                    results.add(m);
                }
            }
        }

        return results;
    }

    @Override
    public ArrayList<Medium> findMediumByDescribtion(String desc) {
        ArrayList results = new ArrayList<Medium>();
        if (mdata.getMediaList().size() > 0) {
            for (Medium m: mdata.getMediaList()) {
                if (m.getDescription() != null && m.getDescription().contains(desc)) {
                    results.add(m);
                }
            }
        }

        return results;
    }

    @Override
    public ArrayList<Copy> findCopyByLocation(String loc) {
        ArrayList<Copy> results = new ArrayList<>();
        if (mdata.getCopyList().size() > 0) {
            for (Copy c: mdata.getCopyList()) {
                if (c.getLocation() != null && c.getLocation().contains(loc)) {
                    results.add(c);
                }
            }
        }

        return results;
    }

    @Override
    public ArrayList<Copy> findCopyByBorrower(User borrower) {
        ArrayList<Copy> results = new ArrayList<>();
        if (mdata.getCopyList().size() > 0) {
            for (Copy c: mdata.getCopyList()) {
                if (c.getBorrowedBy() != null && c.getBorrowedBy() == borrower) {
                    results.add(c);
                }
            }
        }

        return results;
    }

    @Override
    public String editBook(String isbn, String titel, String author, String description, String token) {
        isbn = isbn.replaceAll("-", "");
        System.out.print("\n" + titel);
        User curUser = userValidation.getUserForToken(token);
        if (checkUserOK(curUser)) {
            Book toBeEdited = findMediumByISBN(isbn);
            if (toBeEdited != null) {
                if (titel == null || titel.length() == 0) {
                    return "no Titel";
                } else
                if (author == null || author.length() == 0) {
                    return "no Author";
                } else {
                    toBeEdited.setTitel(titel);
                    toBeEdited.setAuthor(author);
                    if (description != null) {
                        toBeEdited.setDescription(description);
                    }
                }
            } else {
                return "no Book found";
            }
        } else {
            return "invalid User";
        }
        mdata.saveChanges();
        return "OK";
    }

    @Override
    public String editDisc(String barcode, String titel, String director, int fsk, String description, String token) {
        barcode = barcode.replaceAll("-", "");
        User curUser = userValidation.getUserForToken(token);
        if (checkUserOK(curUser)) {
            Disc toBeEdited = findMediumByBarcode(barcode);
            if (toBeEdited != null) {
                if (titel == null) {
                    return "no Titel";
                } else
                if (director == null) {
                    return "no Director";
                } else {
                    toBeEdited.setTitel(titel);
                    toBeEdited.setDirector(director);
                    toBeEdited.setFsk(fsk);
                    if (description != null) {
                        toBeEdited.setDescription(description);
                    }
                }
            } else {
                return "no Disc found";
            }
        }
        mdata.saveChanges();
        return "OK";
    }

    /**
     * chcks if the Isbn is valid.
     * @param isbn the isbn that should be checked
     * @return true if the isbn is valid otherwise false
     */
    private boolean checkValidISBN(String isbn) {
        return (isbn.length() == ISBN_LENGTH) && (isbn.charAt(ISBN_LENGTH - 1) == isbn13CheckDigit(isbn));

    }

    /**
     * checks if a Barcode is valid.
     * @param barcode the barcode that should be checked
     * @return true if it is valid otherwise false
     */
    private boolean checkValidBarcode(String barcode) {
        return (barcode.length() == ISBN_LENGTH) && (barcode.charAt(ISBN_LENGTH - 1) == isbn13CheckDigit(barcode));
    }

    /**
     * checks if a user is activated.
     * @param user the user to be chescked
     * @return true if the user is activated otherwise false
     */
    private boolean checkUserOK(User user) {
        if (user != null) {
            return user.isActivated();
        } else {
            return false;
        }
    }


    /**
     * determiens the check char at the end of a given isbn or barcode
     * @param str the code to be checked
     * @return the char that should eb at the 13th position if the string is valid
     */
    private char isbn13CheckDigit(String str) {
        int sum = 0;
        int digits = 0;
        int multiplier = 1;
        for (int i = 0; i < str.length() && digits < ISBN_LENGTH - 1; i++) {
            char c = str.charAt(i);
            if ('0' <= c && c <= '9') {
                sum += multiplier * (c - '0');
                multiplier ^= 2;
                digits += 1;
            }
        }
        int checkDigit = (TEN - (sum % TEN)) % TEN;
        return (char) (checkDigit + '0');
    }

}
