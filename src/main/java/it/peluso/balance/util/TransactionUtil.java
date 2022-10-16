package it.peluso.balance.util;

import it.peluso.balance.entity.Category;
import it.peluso.balance.entity.Transaction;
import it.peluso.balance.exception.BalanceErrors;
import it.peluso.balance.exception.transaction.InvalidBusinessTransactionException;
import it.peluso.balance.model.CategoryModel;
import it.peluso.balance.model.TransactionModel;
import it.peluso.balance.model.request.TransactionRequest;

public class TransactionUtil {

    public static boolean isTransactionValid(TransactionRequest transaction) {
        return  transaction != null &&
                transaction.getTransactionType() != null &&
                transaction.getTransactionDate() != null &&
                transaction.getAmount() != 0 &&
                transaction.getCategory() !=null;

    }

    public static Category categoryRequestToEntity(CategoryModel categoryModel){
        Category category = new Category();
        category.setId(categoryModel.getId());
        category.setCategory(categoryModel.getCategory());
        return category;
    }

    public static Transaction transactionRequestToEntity(TransactionRequest transactionRequest) throws InvalidBusinessTransactionException {

        if (!isTransactionValid(transactionRequest))
            throw new InvalidBusinessTransactionException(BalanceErrors.ERR_TRANSACTION_INVALID.message);

        Category category = categoryRequestToEntity(transactionRequest.getCategory());
        return Transaction.builder()
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

    public static TransactionModel transactionRequestToModel(TransactionRequest request){
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(request.getCategory().getId());
        categoryModel.setCategory(request.getCategory().getCategory());
        return TransactionModel.builder()
                .transactionDate(request.getTransactionDate())
                .transactionType(request.getTransactionType())
                .category(categoryModel)
                .amount(request.getAmount())
                .description(request.getDescription())
                .build();
    }

//    public TransactionList transactionListMapper(List<Transaction> transactions){
//
//    }
//    public static TransactionResponse transactionEntityToResponse(Transaction transaction) {
//        return TransactionResponse.builder()
//                .transactionType(transactionRequest.getTransactionType())
//                .amount(transactionRequest.getAmount())
//                .transactionDate(transactionRequest.getTransactionDate())
//                .category(
//                        Category.builder().
//                                category(transactionRequest.getCategory())
//                                .build()
//                ).build();
//    }

//    public static Transaction transactionRequestToResponse(TransactionRequest transactionRequest) {
//        return TransactionResponse.builder().errorMessage()
//    }
}
