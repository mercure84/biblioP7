package com.biblioP7.dao;


import com.biblioP7.beans.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleDao extends JpaRepository<AppRole, Integer> {

    AppRole findByRoleName(String roleName);



}
