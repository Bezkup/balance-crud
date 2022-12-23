package it.dreamteam.balance.exception;

public enum BalanceErrors {
    ERR_CATEGORY_ALREADY_EXISTS("La categoria {0} è già esistente."),
    ERR_CATEGORY_NOT_FOUND_BY_ID("La categoria con ID {0} non è esistente."),
    ERR_CATEGORY_NOT_FOUND_BY_NAME("La categoria {0} non è esistente."),
    ERR_TRANSACTION_INVALID("La transazione non è valida."),
    ERR_USERNAME_ALREADY_EXISTS("L'username {0} è già esistente, riprovare con un altro."),
    ZZZ(""); //to end enum


    public final String message;
    BalanceErrors(String message) {
        this.message = message;
    }
}
