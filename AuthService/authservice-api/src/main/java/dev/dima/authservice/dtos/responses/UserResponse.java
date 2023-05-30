package dev.dima.authservice.dtos.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserResponse {

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String phoneNumber;

    private final LocalDate dateOfBirth;

    private final List<? extends GrantedAuthority> roles;
}
