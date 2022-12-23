package it.dreamteam.balance.exception.user;

import it.dreamteam.balance.exception.BalanceErrors;

import java.text.MessageFormat;

public class UsernameAlreadyExists extends Exception {
    public final BalanceErrors error;
    public UsernameAlreadyExists(BalanceErrors error){
        super(error.message);
        this.error = error;
    }

    public UsernameAlreadyExists(BalanceErrors error, Object... params) {
        super(MessageFormat.format(error.message, params));
        this.error = error;
    }
}
