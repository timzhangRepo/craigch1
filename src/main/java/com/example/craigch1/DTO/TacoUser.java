package com.example.craigch1.DTO;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class TacoUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String username;
    private  String password;
    private  String fullname;

    private  String city;
    private  String state;
    private  String phonenumber;

    private Set<String> roles = new HashSet<>();

    public TacoUser(){};
    public TacoUser(String username, String password, String fullname, String city, String state, String phonenumber) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.city = city;
        this.state = state;
        this.phonenumber = phonenumber;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        roles.add("USER");
        return roles.stream().map(role->
                new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
