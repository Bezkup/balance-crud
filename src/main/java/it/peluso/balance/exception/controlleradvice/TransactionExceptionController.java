package it.peluso.balance.exception.controlleradvice;


import it.peluso.balance.exception.ErrorResponse;
import it.peluso.balance.exception.InvalidBusinessTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TransactionExceptionController {

    @ExceptionHandler(value = InvalidBusinessTransactionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleInvalidTransactionException(InvalidBusinessTransactionException ex)
    {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleException(HttpMessageNotReadableException ex)
    {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "JSON invalid, please check and try again.");
    }

}
