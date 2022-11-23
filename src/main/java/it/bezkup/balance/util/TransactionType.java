package it.peluso.balance.util;

import lombok.Getter;

@Getter
public enum TransactionType {

    IN("in"),
    OUT("out");

    private final String type;

    private TransactionType(String type){
        this.type = type;
    }
}
