package com.ftn.FitnesTraining.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoggedInUserDTO {
    private int id;
    private String token;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public LoggedInUserDTO(int id, String token, String username, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.email = email;
        this.authorities = authorities;
    }
}
