package dev.dima.authservice.models;

import dev.dima.authservice.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "date_created", updatable = false)
    @Transient
    private LocalDate dateCreated;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    public void setRole(UserRole userRole) {
        if(userRoles == null) {
            userRoles = new HashSet<>();
        }
        userRoles.add(userRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream()
                .map(UserRole::getRole)
                .collect(Collectors.toSet());
    }

    // Для mapstruct
    public Collection<? extends GrantedAuthority> getUserRoles() {
        return getAuthorities();
    }

    @Override
    public String getUsername() {
        if(phoneNumber != null) {
            return email;
        } else {
            return email;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
