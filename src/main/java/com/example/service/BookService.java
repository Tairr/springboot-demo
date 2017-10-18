package com.example.service;

import com.example.domain.Book;

/**
 * Created by tangjinhui on 2017/10/13.
 */
public interface BookService {

    Book getByAuthor(String author);

}
