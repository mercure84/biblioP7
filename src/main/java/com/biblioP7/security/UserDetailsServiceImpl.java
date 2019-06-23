package com.biblioP7.security;

import com.biblioP7.beans.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//classe qui vérifie la concordance des utilisateurs et de leurs rôles

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Membre membre = accountService.findUserByUsername(username);
        if(membre == null) throw new UsernameNotFoundException((username));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        membre.getRoles().forEach((r->{
            authorities.add(new SimpleGrantedAuthority((r.getRoleName())));
        }));

        return new User(membre.getEmail(), membre.getEncryptedPassword(), authorities);





    }
}