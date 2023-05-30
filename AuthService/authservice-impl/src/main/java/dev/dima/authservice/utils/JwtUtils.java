package dev.dima.authservice.utils;

import dev.dima.authservice.models.JwtAuthentication;
import dev.dima.authservice.models.enums.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUserId(UUID.fromString(claims.getSubject()));
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", ArrayList.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

}