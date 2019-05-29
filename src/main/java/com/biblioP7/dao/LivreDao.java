package com.biblioP7.dao;

import com.biblioP7.beans.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreDao extends JpaRepository<Livre, Integer> {

    Livre findById(int it);
    List<Livre> findLivresByDisponibleIsTrue();


}
