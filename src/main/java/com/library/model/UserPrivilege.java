package src.main.java.com.library.model;


public class UserPrivilege {
    private int userID;
    private int permID;

    public UserPrivilege() {}

    public UserPrivilege(int userID, int permID) {
        this.userID = userID;
        this.permID = permID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPermID() {
        return permID;
    }

    public void setPermID(int permID) {
        this.permID = permID;
    }
}

