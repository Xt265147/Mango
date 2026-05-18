package com.example.demo.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUserDetails implements UserDetails {
    @Getter@Setter
    private String username;
    @Getter@Setter
    private String password;
    @Getter@Setter
    private String salt;
    @Getter@Setter
    private Collection<? extends GrantedAuthority> authorities;
    @Setter
    private boolean enabled;

    public JwtUserDetails() {
    }

    public JwtUserDetails(String username, String password, String salt,
                          Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.authorities = authorities;
        this.enabled = true;
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
        return enabled;
    }
}
