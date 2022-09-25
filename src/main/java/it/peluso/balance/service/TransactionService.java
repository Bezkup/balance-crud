package it.peluso.balance.service;

import it.peluso.balance.exception.InvalidBusinessTransactionException;
import it.peluso.balance.model.TransactionModel;
import it.peluso.balance.model.request.TransactionRequest;
import it.peluso.balance.entity.Transaction;
import it.peluso.balance.model.response.TransactionResponse;
import it.peluso.balance.repository.TransactionRepository;
import it.peluso.balance.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }

    public TransactionResponse findAllTransactionsBetweenDates(LocalDate startDate, LocalDate endDate){
        List<Transaction> transactions;
        List<TransactionModel> transactionModels = new ArrayList<>();

        if(startDate == null) {
            startDate = LocalDate.EPOCH;
        }
        if(endDate == null) {
            endDate = LocalDate.now();
        }

        transactions = repository.findTransactionsByTransactionDateBetween(startDate, endDate);

        if(transactions.isEmpty()) {
            return new TransactionResponse();
        }

        transactions.forEach(transaction -> transactionModels.add(
                TransactionUtil.transactionEntityToResponseModel(transaction)
        ));

        return new TransactionResponse(transactionModels);
    }

    public TransactionResponse saveTransaction(TransactionRequest transactionRequest) throws InvalidBusinessTransactionException {
        Transaction transaction = TransactionUtil.transactionRequestToEntity(transactionRequest);
        Transaction response = repository.save(transaction);
        return new TransactionResponse(
                Collections.singletonList(TransactionUtil.transactionEntityToResponseModel(response))
        );
    }
}
