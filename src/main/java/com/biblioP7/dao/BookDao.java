package com.biblioP7.dao;

import com.biblioP7.beans.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

    Book findById(int it);
    List<Book> findBooksByAuthor(String author);


}
