package com.assigment.springboot.bookmanagement.service.classes;

import com.assigment.springboot.bookmanagement.dao.BookUserRepository;
import com.assigment.springboot.bookmanagement.entity.BookUser;
import com.assigment.springboot.bookmanagement.exceptions.MyRuntimeException;
import com.assigment.springboot.bookmanagement.service.interfaces.BookUserService;
import com.assigment.springboot.bookmanagement.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookUserServiceImpl implements BookUserService {
    private BookUserRepository bookUserRepository;

    @Autowired
    public BookUserServiceImpl(BookUserRepository theBookUserRepository){
        bookUserRepository=theBookUserRepository;
    }

    @Override
    public List<BookUser> findAll() {
        return bookUserRepository.findAll();
    }

    @Override
    public BookUser findById(int theId) throws MyRuntimeException {
        Optional<BookUser> result = bookUserRepository.findById(theId);
        BookUser theBookUser=null;
        if(result.isPresent()){
            theBookUser=result.get();
        }
        else{
            throw new MyRuntimeException("did not find bookuser id "+ theId);
        }
        return theBookUser;
    }

    @Override
    public void save(BookUser theBookUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails principal = (CustomUserDetails) auth.getPrincipal();
        theBookUser.setUserId(principal.getId());
        bookUserRepository.save(theBookUser);
    }

    @Override
    public void deleteById(int theId) {
        bookUserRepository.deleteById(theId);
    }

}
