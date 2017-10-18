package com.example.domain;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tangjinhui on 2017/10/13.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue
    private  Long  id;
    private  String  title;
    private  String  author;

    public Long  getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
