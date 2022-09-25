package it.peluso.balance.exception;

public enum BalanceErrors {
    ERR_TRANSACTION_INVALID("Transaction is not valid."),
    ZZZ(""); //to end enum


    public final String message;
    BalanceErrors(String message) {
        this.message = message;
    }
}
