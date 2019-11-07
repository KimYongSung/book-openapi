package com.kys.openapi.user.dto;

import com.kys.openapi.user.code.Role;
import com.kys.openapi.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OpenApiUserDetail implements UserDetails {

    private User user;

    private Set<Role> role = new HashSet<>();

    private OpenApiUserDetail(User user) {
        this.user = user;
        role.add(Role.USER);
    }

    public static OpenApiUserDetail of(User user){
        return new OpenApiUserDetail(user);
    }

    public List<String> getRoles(){
        return role.stream()
                   .map(Role::name)
                   .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream()
                   .map(role1 -> new SimpleGrantedAuthority(role1.name()))
                   .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getUserPwd();
    }

    @Override
    public String getUsername() {
        return String.valueOf(user.getUserNo());
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
}
