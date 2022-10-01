package it.peluso.balance.model;

import it.peluso.balance.entity.Category;
import it.peluso.balance.util.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransactionModel {

    protected TransactionType transactionType;

    protected int amount;

    protected LocalDate transactionDate;

    protected String description;

    protected CategoryModel category;

    public TransactionModel(){

    }

    public TransactionModel(
            TransactionType transactionType,
            int amount,
            LocalDate transactionDate,
            CategoryModel category
    ) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.category = category;
    }

    public TransactionModel(
            TransactionType transactionType,
            int amount,
            LocalDate transactionDate,
            String description,
            CategoryModel category
    ) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.description = description;
        this.category = category;
    }
}
