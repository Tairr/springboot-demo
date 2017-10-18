package com.example.dao;

import com.example.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by tangjinhui on 2017/10/13.
 */

@Repository
public interface BookDao extends JpaRepository<Book,Integer> {


    @Query("from Book b where b.author=:author")
    public Book findByAuthor(@Param("author") String author);
}

