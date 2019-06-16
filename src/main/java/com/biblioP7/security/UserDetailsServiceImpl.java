package com.biblioP7.security;

import com.biblioP7.beans.Membre;
import com.biblioP7.dao.MembreDao;
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


//    Dans le cas où on utilise une table dédiée aux rôles... ce qui n'est pas le cas dans notre P7
//    @Autowired
//    private AppRoleDAO appRoleDAO;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Membre membre = this.membreDao.findByEmail(email);

        if (membre == null) {
            throw new UsernameNotFoundException("User " + membre.getPrenom() + " " + membre.getPrenom() + " " + " " + membre.getEmail() + " was not found in the database");
        }


        // [ROLE_USER, ROLE_ADMIN,..]
        String roleName = membre.getRole();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        if (roleName != null) {
            GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                grantList.add(authority);

        }

        UserDetails userDetails = (UserDetails) new User(membre.getEmail(), membre.getEncryptedPassword(), grantList);

        return userDetails;

    }


}