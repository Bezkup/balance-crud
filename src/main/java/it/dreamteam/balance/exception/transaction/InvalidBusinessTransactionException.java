package it.dreamteam.balance.exception.transaction;

public class InvalidBusinessTransactionException extends Exception {
    public InvalidBusinessTransactionException(String message){
        super(message);
    }
}