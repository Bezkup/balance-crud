package it.peluso.balance.exception;

public class InvalidBusinessTransactionException extends Exception {
    public InvalidBusinessTransactionException(String message){
        super(message);
    }
}
