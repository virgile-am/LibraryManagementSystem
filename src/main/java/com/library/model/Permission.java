package src.main.java.com.library.model;

public class Permission {
    private int permID;
    private String permName;

    public Permission() {}

    public Permission(int permID, String permName) {
        this.permID = permID;
        this.permName = permName;
    }

    public int getPermID() {
        return permID;
    }

    public void setPermID(int permID) {
        this.permID = permID;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }
}



