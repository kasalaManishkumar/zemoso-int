package com.assigment.springboot.bookmanagement;

import com.assigment.springboot.bookmanagement.dao.BookUserRepository;
import com.assigment.springboot.bookmanagement.entity.BookUser;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.classes.BookUserServiceImpl;
import com.assigment.springboot.bookmanagement.service.interfaces.BookUserService;
import lombok.var;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
 class BookUserServiceImplTest {


//    @Before
//    public void setupMock() {
//        MockitoAnnotations.initMocks(this);
//    }


    @Mock
    private BookUserRepository bookUserRepository;

    @InjectMocks
    private BookUserServiceImpl bookUserService;



    @Test
    void findAll() {
        bookUserService = new BookUserServiceImpl(bookUserRepository);
        List<BookUser> bookUserList = new ArrayList<>();

        BookUser bookUser1=new BookUser(1,1);
        BookUser bookUser2=new BookUser(2,1);
        bookUserList.add(bookUser1);
        bookUserList.add(bookUser2);
        when(bookUserService.findAll()).thenReturn(bookUserList);
        List<BookUser> expectedList = bookUserService.findAll();
        assertEquals(2, expectedList.size());
    }
    @Test
    void findById() throws MyRuntimeException {

        when(bookUserRepository.findById(1)).thenReturn(Optional.of(new BookUser(1,2)));
        BookUser bookUser=bookUserService.findById(1);
        Assertions.assertThat(bookUser.getBookId()).isEqualTo(1);
        Assertions.assertThat(bookUser.getQuantity()).isEqualTo(2);


    }



    @Test
    void deleteById(){
        bookUserService.deleteById(1);
        verify(bookUserRepository).deleteById(1);
    }
}
