package it.peluso.balance.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;

    public ErrorResponse(String message)
    {
        this.message = message;
    }
}
