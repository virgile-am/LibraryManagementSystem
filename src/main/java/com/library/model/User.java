package src.main.java.com.library.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final IntegerProperty userID;
    private final StringProperty username;
    private final StringProperty password;
    private final IntegerProperty roleID;

    public User(int userID, String username, String password, int roleID) {
        this.userID = new SimpleIntegerProperty(userID);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.roleID = new SimpleIntegerProperty(roleID);
    }

    // Getter methods for JavaFX properties
    public int getUserID() {
        return userID.get();
    }

    public IntegerProperty userIDProperty() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public int getRoleID() {
        return roleID.get();
    }

    public IntegerProperty roleIDProperty() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID.set(roleID);
    }
    @Override
    public String toString() {
        return getUsername();
    }
}
