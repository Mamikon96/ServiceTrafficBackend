package com.edu.web.services.impls;

import com.edu.web.entities.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private Map<User, UsernamePasswordAuthenticationToken> usersMap = new HashMap<>();

    public boolean authenticate(User user, UsernamePasswordAuthenticationToken token) {
        if (!usersMap.containsKey(user)) {
            usersMap.put(user, token);
        }
        return true;
    }

    public UsernamePasswordAuthenticationToken getToken(User user) {
        if (usersMap.containsKey(user)) {
            return usersMap.get(user);
        }
        return null;
    }
}
