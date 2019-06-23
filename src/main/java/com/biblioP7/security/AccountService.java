package com.biblioP7.security;

import com.biblioP7.beans.AppRole;
import com.biblioP7.beans.Membre;

public interface AccountService {

    public Membre saveMembre(Membre membre);
    public AppRole saveRole(AppRole role);
    public void addRoleToMembre(String username, String roleName);
    public Membre findUserByUsername(String username);

}
