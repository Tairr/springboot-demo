package com.example.service;

import com.example.dao.BookDao;
import com.example.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tangjinhui on 2017/10/13.
 */
@Service("bookService")
public class BookServiceImp implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public Book getByAuthor(String author){
        return  bookDao.findByAuthor(author);
    }
}
