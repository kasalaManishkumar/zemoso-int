package com.assigment.springboot.bookmanagement.dao;

import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book,Integer> {
}