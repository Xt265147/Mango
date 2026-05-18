package com.example.demo.service.impl;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限封装
 */
@Setter
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;
    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
