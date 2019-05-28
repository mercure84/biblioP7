package com.biblioP7.dao;


import com.biblioP7.beans.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpruntDao extends JpaRepository<Emprunt, Integer> {



}
