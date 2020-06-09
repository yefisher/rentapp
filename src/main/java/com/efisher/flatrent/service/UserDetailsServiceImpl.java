package com.efisher.flatrent.service;

import com.efisher.flatrent.domain.User;
import com.efisher.flatrent.domain.UserRole;
import com.efisher.flatrent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    private final HttpServletRequest request;

    @Autowired
    public UserDetailsServiceImpl(final UserRepository repository, HttpServletRequest request) {
        this.repository = repository;
        this.request = request;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);

        if (!user.isPresent())
            throw new UsernameNotFoundException(String.format("Such user with given username: %s not found!", username));

        User retrievedUser = user.get();
        List<GrantedAuthority> authorities = getGrantedAuthorities(retrievedUser.getRoles());

        return new org.springframework.security.core.userdetails.User(retrievedUser.getUsername(),
                retrievedUser.getPassword(), authorities);
    }

    private List<GrantedAuthority> getGrantedAuthorities(final List<UserRole> roles) {
        final List<GrantedAuthority> authorities = new ArrayList<>();

        for(UserRole role : roles)
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return authorities;
    }

    /*
    * This method will be used in further updates :)
    * */

    private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
