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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(final UserRepository repository) {
        this.repository = repository;
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

        for (UserRole role : roles)
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        return authorities;
    }
}
