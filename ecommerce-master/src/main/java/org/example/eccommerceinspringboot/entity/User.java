package org.example.eccommerceinspringboot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.example.eccommerceinspringboot.entity.abs.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

    private String role;

    private Integer age;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
