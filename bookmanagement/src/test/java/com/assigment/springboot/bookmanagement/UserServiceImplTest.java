package com.assigment.springboot.bookmanagement;

import com.assigment.springboot.bookmanagement.dao.UserRepository;
import com.assigment.springboot.bookmanagement.entity.User;
import com.assigment.springboot.bookmanagement.service.classes.UserServiceImpl;
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
 class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findAll() {
        userService = new UserServiceImpl(userRepository);
        List<User> userList = new ArrayList<>();
        User user1=new User("manish","hello123","ADMIN");
        User user2=new User("kittu","hello123","CUSTOMER");
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> expectedList = userService.findAll();
        assertEquals(2, expectedList.size());
    }

    @Test
    void findById(){

        when(userRepository.findById(1)).thenReturn(Optional.of(new User(1, "ADMIN", "manish", "hello123")));
        User user=userService.findById(1);
        Assertions.assertThat(user.getUsername()).isEqualTo("manish");
        Assertions.assertThat(user.getPassword()).isEqualTo("hello123");
    }

    @Test
    void save() {
        User user1=new User("manish","hello123","ADMIN");
        userService.save(user1);
        verify(userRepository).save(user1);
    }
    @Test
    void deleteById(){
        userService.deleteById(1);
        verify(userRepository).deleteById(1);
    }
}
