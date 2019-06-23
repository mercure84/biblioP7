package com.biblioP7.security;


import com.biblioP7.beans.AppRole;
import com.biblioP7.beans.Membre;
import com.biblioP7.dao.AppRoleDao;
import com.biblioP7.dao.MembreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MembreDao membreDao;

    @Autowired
    private AppRoleDao appRoleDao;

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleDao.save(role);
    }

    @Override
    public Membre saveMembre(Membre membre) {
        String hashPW = bCryptPasswordEncoder.encode(membre.getEncryptedPassword());
        membre.setEncryptedPassword(hashPW);
        return membreDao.save(membre);
    }

    @Override
    public void addRoleToMembre(String username, String roleName) {
        AppRole role = appRoleDao.findByRoleName((roleName));
        Membre membre = membreDao.findByEmail(username);
        membre.getRoles().add(role);
    }

    @Override
    public Membre findUserByUsername(String username) {
        return membreDao.findByEmail(username);
    }
}
