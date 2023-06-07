package dev.dima.transactionservice.security.authentication.enums;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),
    ROLE_BETTING_MANAGER("BETTING_MANAGER"),
    ROLE_BOSS("BOSS");

    private final String value;

    @Override
    public String getAuthority() {
        return this.name();
    }

}