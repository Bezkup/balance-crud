package it.dreamteam.balance.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    private String username;

    private String password;

    private String firstName;
    private String lastName;
    private String email;
}