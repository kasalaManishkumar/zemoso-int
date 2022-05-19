package com.assigment.springboot.bookmanagement;

import com.assigment.springboot.bookmanagement.dao.BookRepository;
import com.assigment.springboot.bookmanagement.entity.Book;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.classes.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
 class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void findAll() {
        bookService = new BookServiceImpl(bookRepository);

        List<Book> bookList = new ArrayList<>();

        Book book1=new Book("Attitude is verything","Motivational",200,120);
        Book book2=new Book("In a home","Horror",510,290);


        bookList.add(book1);
        bookList.add(book2);


        when(bookService.findAll()).thenReturn(bookList);

        List<Book> expectedList = bookService.findAll();

        assertEquals(2, expectedList.size());
    }

    @Test
    void findById() throws MyRuntimeException {
        bookService = new BookServiceImpl(bookRepository);
        List<Book> bookList = new ArrayList<>();
        Book book1=new Book(1,"Wings of fire","biography",200,180);
        bookList.add(book1);
        when(bookRepository.findById(1)).thenReturn(Optional.ofNullable(bookList.get(0)));
        Book book = bookService.findById(1);

        assertEquals(1, book.getId());
        assertEquals("Wings of fire",book.getBookName());

    }

    @Test
    void save() {
        Book book1=new Book(1,"new start","motivation",180,110);
        bookService.save(book1);
        verify(bookRepository).save(book1);
    }

    @Test
    void deleteById(){
        bookService.deleteById(1);
        verify(bookRepository).deleteById(1);
    }


}
