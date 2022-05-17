package com.assigment.springboot.bookmanagement.dao;

import com.assigment.springboot.bookmanagement.entity.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookUserRepository extends JpaRepository<BookUser,Integer> {

}
