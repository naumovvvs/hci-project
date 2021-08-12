package mk.ukim.finki.diettastic.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_BASIC, ROLE_NUTRITIONIST;

    @Override
    public String getAuthority() {
        return name();
    }
}
