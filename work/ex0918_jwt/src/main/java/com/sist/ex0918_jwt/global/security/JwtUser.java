package com.sist.ex0918_jwt.global.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class JwtUser extends User{
    @Getter
    private String mid;

    public JwtUser(String mid, String username, String password,
        Collection<? extends GrantedAuthority> authorities){
            super(username, password, authorities);
            this.mid = mid;
    }

    public Authentication getAuthentication(){
        Authentication auth = new UsernamePasswordAuthenticationToken(
            this, this.getPassword(), this.getAuthorities());

        return auth;
    }
}
