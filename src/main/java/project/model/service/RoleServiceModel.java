package project.model.service;

public class RoleServiceModel extends BaseServiceModel {
    private String authority;
    private String userId;

    public RoleServiceModel() {
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
