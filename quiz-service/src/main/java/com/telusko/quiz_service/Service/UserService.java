package com.telusko.quiz_service.Service;


import com.netflix.discovery.converters.Auto;
import com.telusko.quiz_service.DAO.UserDao;
import com.telusko.quiz_service.Model.Response;
import com.telusko.quiz_service.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao dao;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public ResponseEntity<String> add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = dao.save(user);
        if(saved != null){
            return new ResponseEntity<>("SAVED", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("NOT SAVED", HttpStatus.NOT_FOUND);
        }

    }


}
