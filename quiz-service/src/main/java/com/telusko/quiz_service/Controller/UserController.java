package com.telusko.quiz_service.Controller;


import com.netflix.discovery.converters.Auto;
import com.telusko.quiz_service.DAO.UserDao;
import com.telusko.quiz_service.Model.Response;
import com.telusko.quiz_service.Model.User;
import com.telusko.quiz_service.Service.JwtService;
import com.telusko.quiz_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;




    @PostMapping("register")
    public ResponseEntity<String> addUser(@RequestBody User user){
      return service.add(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "FAILED";
        }
    }
}
