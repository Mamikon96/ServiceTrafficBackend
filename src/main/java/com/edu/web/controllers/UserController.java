package com.edu.web.controllers;

import com.edu.web.entities.User;
import com.edu.web.services.impls.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        /*authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );*/

//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
////        String token = UUID.randomUUID().toString();
//        userService.authenticate(user, token);
//        String username = user.getUsername();
//        Map<Object, Object> model = new HashMap<>();
//        model.put("username", username);
//        model.put("token", token);
//        return ok(model);

        return user.getUsername().equals("user") && user.getPassword().equals("password");
    }

    @PostMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
}
