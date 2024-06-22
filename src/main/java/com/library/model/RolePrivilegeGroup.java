package src.main.java.com.library.model;


public class RolePrivilegeGroup {
    private int roleID;
    private int groupID;

    public RolePrivilegeGroup() {}

    public RolePrivilegeGroup(int roleID, int groupID) {
        this.roleID = roleID;
        this.groupID = groupID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
}
