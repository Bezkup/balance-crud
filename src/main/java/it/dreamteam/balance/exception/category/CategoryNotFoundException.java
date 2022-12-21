package it.dreamteam.balance.exception.category;

import it.dreamteam.balance.exception.BalanceErrors;

import java.text.MessageFormat;

public class CategoryNotFoundException extends Exception {
    public final BalanceErrors error;
    public CategoryNotFoundException(BalanceErrors error){
        super(error.message);
        this.error = error;
    }

    public CategoryNotFoundException(BalanceErrors error, Object... params) {
        super(MessageFormat.format(error.message, params));
        this.error = error;
    }
}