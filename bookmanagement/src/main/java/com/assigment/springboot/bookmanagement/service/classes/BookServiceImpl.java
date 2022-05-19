package com.assigment.springboot.bookmanagement.service.classes;

import com.assigment.springboot.bookmanagement.dao.BookRepository;
import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository){
        bookRepository=theBookRepository;
    }

    public BookServiceImpl() {
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int theId) throws MyRuntimeException {
        Optional<Book> result = bookRepository.findById(theId);
        Book theBook=null;
        if(result.isPresent()){
            theBook=result.get();
        }
        else{
            throw new MyRuntimeException("did not find book id "+ theId);
        }
        return theBook;
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }



}

