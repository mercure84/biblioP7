package com.biblioP7.security;

import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//classe qui vérifie la concordance des utilisateurs et de leurs rôles

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MembreDao membreDao;

// Nous n'utilisons pas de table listant les rôle car chaque membre ne peut avoir qu'un seul rôle (à définir avec le métier)
//    @Autowired
//    private AppRoleDAO appRoleDAO;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Membre membre = this.membreDao.findByEmail(email);

        if (membre == null) {
            logger.warn("User not found! " + email);
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }

        logger.info("Found User: " + membre);

        // [ROLE_USER, ROLE_ADMIN,..]
        String roleMembre = membre.getRole();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        if (roleMembre != null) {
        {       // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(roleMembre);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(membre.getEmail(), membre.getEncryptedPassword(), grantList);

        return userDetails;

    }


}