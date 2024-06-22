package src.main.java.com.library.model;

public class Patron {
    private int patronID;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Patron() {}

    public Patron(int patronID, String name, String email, String phone, String address) {
        this.patronID = patronID;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getPatronID() {
        return patronID;
    }

    public void setPatronID(int patronID) {
        this.patronID = patronID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
