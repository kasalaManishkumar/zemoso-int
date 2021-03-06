package com.assigment.springboot.bookmanagement.service.classes;

import com.assigment.springboot.bookmanagement.dao.UserRepository;
import com.assigment.springboot.bookmanagement.entity.User;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository=theUserRepository;
        passwordEncoder=new BCryptPasswordEncoder();
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) throws MyRuntimeException {
        Optional<User> result = userRepository.findById(theId);
        User theUser=null;
        if(result.isPresent()){
            theUser=result.get();
        }
        else{
            throw new MyRuntimeException("did not find user id "+ theId);
        }
        return theUser;
    }

    @Override
    public void save(User theUser) {
        String encodedPassword = this.passwordEncoder.encode(theUser.getPassword());
        theUser.setPassword(encodedPassword);
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }
}
