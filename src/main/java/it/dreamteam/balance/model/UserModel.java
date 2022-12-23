package it.dreamteam.balance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
