package com.assigment.springboot.bookmanagement;

import com.assigment.springboot.bookmanagement.dao.BookUserRepository;
import com.assigment.springboot.bookmanagement.entity.BookUser;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.classes.BookUserServiceImpl;
import org.assertj.core.api.Assertions;
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
 class BookUserServiceImplTest {

    @Mock
    private BookUserRepository bookUserRepository;

    @InjectMocks
    private BookUserServiceImpl bookUserService;

    @Test
    void findAll() {
        bookUserService = new BookUserServiceImpl(bookUserRepository);
        List<BookUser> bookUserList = new ArrayList<>();

        BookUser bookUser1=new BookUser(1,1,2);
        BookUser bookUser2=new BookUser(2,1,3);
        bookUserList.add(bookUser1);
        bookUserList.add(bookUser2);
        when(bookUserService.findAll()).thenReturn(bookUserList);
        List<BookUser> expectedList = bookUserService.findAll();
        assertEquals(2, expectedList.size());
    }
    @Test
    void findById() throws MyRuntimeException {

        when(bookUserRepository.findById(1)).thenReturn(Optional.of(new BookUser(1,2,3)));
        BookUser bookUser=bookUserService.findById(1);
        Assertions.assertThat(bookUser.getBookId()).isEqualTo(1);
        Assertions.assertThat(bookUser.getUserId()).isEqualTo(2);
        Assertions.assertThat(bookUser.getQuantity()).isEqualTo(3);


    }

//    @Test
//    void save() {
//        BookUser bookUser1=new BookUser(1,2,3);
//        bookUserService.save(bookUser1);
//        verify(bookUserRepository).save(bookUser1);
//    }
    @Test
    void deleteById(){
        bookUserService.deleteById(1);
        verify(bookUserRepository).deleteById(1);
    }
}
