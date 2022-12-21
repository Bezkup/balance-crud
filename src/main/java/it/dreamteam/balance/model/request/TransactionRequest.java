package it.dreamteam.balance.model.request;

import it.dreamteam.balance.model.CategoryModel;
import it.dreamteam.balance.model.TransactionModel;
import it.dreamteam.balance.util.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    protected TransactionType transactionType;

    protected int amount;

    protected LocalDate transactionDate;

    protected String description;

    protected String category;
}