package src.main.java.com.library.model;



public class GroupPermission {
    private int groupID;
    private int permID;

    public GroupPermission() {}

    public GroupPermission(int groupID, int permID) {
        this.groupID = groupID;
        this.permID = permID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getPermID() {
        return permID;
    }

    public void setPermID(int permID) {
        this.permID = permID;
    }
}
