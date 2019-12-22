package xmlteam4.Project.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
    private UserRole role;

    public Authority() {
    }

    public Authority(UserRole role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.toString();
    }
}
