package it.dreamteam.balance.controller;

import it.dreamteam.balance.entity.User;
import it.dreamteam.balance.exception.user.UsernameAlreadyExists;
import it.dreamteam.balance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/saveUser")
    public long saveUser(@RequestBody User user) throws UsernameAlreadyExists {
        return service.saveUser(user);
    }
}
