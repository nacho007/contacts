package deandreis.contacts;

/**
 * Created by ignaciodeandreisdenis on 25/8/17.
 */


import java.io.Serializable;

/**
 * Created by ignaciodeandreisdenis on 10/6/16.
 */

public class Contact implements Serializable {

    private String photoUri;
    private String name;
    private String number;
    private boolean isRecent = false;
    private boolean astroPayUser;
    private int contactID;

    public Contact() {
    }

    public Contact(String _name, String _number) {
        name = _name;
        number = _number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String toString() {
        return "name: " + getName() + " numbers: " + getNumber();
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }

    public boolean isRecent() {
        return isRecent;
    }

    public void setRecent(boolean recent) {
        isRecent = recent;
    }

    public boolean isAstroPayUser() {
        return astroPayUser;
    }

    public void setAstroPayUser(boolean astroPayUser) {
        this.astroPayUser = astroPayUser;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Contact)) return false;
        Contact otherMyClass = (Contact) other;

        if (number == null && otherMyClass.number != null) {
            return false;
        } else if (number != null && otherMyClass.number == null) {
            return false;
        } else if (number == null && otherMyClass.number == null) {
            return true;
        } else if (number.replace(" ", "").equals(otherMyClass.number.replace(" ", ""))) {
            return true;
        } else {
            return false;
        }

    }


    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
}
