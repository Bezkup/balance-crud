package it.dreamteam.balance.controller;

import it.dreamteam.balance.entity.User;
import it.dreamteam.balance.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    @DisplayName("Check create user go fine")
    public void checkCreateUserGoFine(){
        User user = new User("Roy", "password", "Roy","Bruschini", "bruroy96@gmail.com");
        assertDoesNotThrow(() -> userService.saveUser(user));
    }
}
