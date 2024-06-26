package src.main.java.com.library.model;

import javafx.beans.property.*;

public class Patron {
    private final IntegerProperty patronID;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty address;

    public Patron(int patronID, String name, String email, String phone, String address) {
        this.patronID = new SimpleIntegerProperty(patronID);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
    }

    public int getPatronID() {
        return patronID.get();
    }

    public IntegerProperty patronIDProperty() {
        return patronID;
    }

    public void setPatronID(int patronID) {
        this.patronID.set(patronID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    // Override toString method to return the name of the patron
    @Override
    public String toString() {
        return getName();
    }

}
