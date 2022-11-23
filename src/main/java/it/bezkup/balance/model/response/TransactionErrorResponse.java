package it.peluso.balance.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TransactionErrorResponse {

    private String type;
    private String title;
    private int status;
    private String detail;
    private String instance;
}
