package com.aquiles.helpdesk.security;

import com.aquiles.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
public class UserSS implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    public UserSS(Integer id, String userName, String password, Set<Perfil> perfis) {
        this.id = id;
        this.password = password;
        this.userName = userName;
        this.authorities =  perfis.stream().map(x -> new SimpleGrantedAuthority((x.getDescricao()))).collect(Collectors.toSet());
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
