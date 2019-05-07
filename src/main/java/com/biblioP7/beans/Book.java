package com.biblioP7.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    private int id;
    private String title;
    private String author;
    private int pubYear;
    private String category;


}
