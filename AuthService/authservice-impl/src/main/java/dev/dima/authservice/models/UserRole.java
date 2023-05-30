package dev.dima.authservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.dima.authservice.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity user;

    @Column(name = "role")
    private Role role;

}
