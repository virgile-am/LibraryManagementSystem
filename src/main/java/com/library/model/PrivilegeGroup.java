package src.main.java.com.library.model;

public class PrivilegeGroup {
    private int groupID;
    private String groupName;

    public PrivilegeGroup() {}

    public PrivilegeGroup(int groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
