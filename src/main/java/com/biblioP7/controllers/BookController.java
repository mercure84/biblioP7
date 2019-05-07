package com.biblioP7.controllers;


import com.biblioP7.beans.Book;
import com.biblioP7.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value="/listeBooks", method= RequestMethod.GET)
    public List<Book> listeBooks(){
        List<Book> books = bookDao.findAll();
        return books;
    }

    @GetMapping(value="/Book/{id}")
    public Book detailBook(@PathVariable int id){
        return bookDao.findById(id);
    }

    @PostMapping(value="/addBook")
    public void addBook(@RequestBody Book book){
        bookDao.save(book);
    }

}
