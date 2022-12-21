package it.dreamteam.balance.util;

import it.dreamteam.balance.entity.Category;
import it.dreamteam.balance.entity.Transaction;
import it.dreamteam.balance.exception.BalanceErrors;
import it.dreamteam.balance.exception.transaction.InvalidBusinessTransactionException;
import it.dreamteam.balance.model.CategoryModel;
import it.dreamteam.balance.model.TransactionModel;
import it.dreamteam.balance.model.request.TransactionRequest;

public class TransactionUtil {

    public static boolean isTransactionValid(TransactionRequest transaction) {
        return  transaction != null &&
                transaction.getTransactionType() != null &&
                transaction.getTransactionDate() != null &&
                transaction.getAmount() != 0 &&
                transaction.getCategory() != null;

    }

    public static Category categoryRequestToEntity(String category){
        return new Category(category);
    }

    public static Transaction transactionRequestToEntity(TransactionRequest transactionRequest) throws InvalidBusinessTransactionException {

        if (!isTransactionValid(transactionRequest))
            throw new InvalidBusinessTransactionException(BalanceErrors.ERR_TRANSACTION_INVALID.message);

        Category category = categoryRequestToEntity(transactionRequest.getCategory());
        return Transaction.builder()
                .description(transactionRequest.getDescription())
                .transactionType(transactionRequest.getTransactionType())
                .amount(transactionRequest.getAmount())
                .transactionDate(transactionRequest.getTransactionDate())
                .category(category)
                .build();

    }

    public static TransactionModel transactionEntityToResponseModel(Transaction transaction) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(transaction.getCategory().getId());
        categoryModel.setCategory(transaction.getCategory().getCategory());
        return TransactionModel.builder()
                .transactionDate(transaction.getTransactionDate())
                .transactionType(transaction.getTransactionType())
                .category(categoryModel)
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .build();
    }

    //TODO
//    public static TransactionModel transactionRequestToModel(TransactionRequest request) {
//        Category category =
//        CategoryModel categoryModel = new CategoryModel(request.getCategory());
//        return TransactionModel.builder()
//                .transactionDate(request.getTransactionDate())
//                .transactionType(request.getTransactionType())
//                .category(categoryModel)
//                .amount(request.getAmount())
//                .description(request.getDescription())
//                .build();
//    }

}
