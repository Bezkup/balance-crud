package it.dreamteam.balance.service;

import it.dreamteam.balance.entity.User;
import it.dreamteam.balance.exception.BalanceErrors;
import it.dreamteam.balance.exception.user.UsernameAlreadyExists;
import it.dreamteam.balance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }


    public Long saveUser(User user) throws UsernameAlreadyExists {
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExists(BalanceErrors.ERR_USERNAME_ALREADY_EXISTS, user.getUsername());
        }
        return repository.save(user).getId();
    }
}
