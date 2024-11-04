package com.telusko.quiz_service.Service;

import com.telusko.quiz_service.DAO.UserDao;
import com.telusko.quiz_service.Model.User;
import com.telusko.quiz_service.Model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            System.out.println("USER NOT FOUND");
            throw new UsernameNotFoundException("USER 404");
        }
        return new UserPrincipal(user);
    }
}
