package com.example.controller;

import com.example.domain.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tangjinhui on 2017/10/13.
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value="/{author}",method= RequestMethod.GET)
    public Book getBookByAuthor(@PathVariable("author") String  author){

        stringRedisTemplate.opsForValue().set("aaa","bbb");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));

        return bookService.getByAuthor(author);
    }
}
