package it.peluso.balance.model.response;

import it.peluso.balance.model.TransactionModel;
import it.peluso.balance.model.request.TransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class TransactionResponse {

    //In case of success
    private List<TransactionModel> result;

    public TransactionResponse(){
        result = new ArrayList<>();
    }
    public TransactionResponse(List<TransactionModel> transactions) {
        result = transactions;
    }
}
