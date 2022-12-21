package it.dreamteam.balance.controller;

import it.dreamteam.balance.exception.category.CategoryNotFoundException;
import it.dreamteam.balance.exception.transaction.InvalidBusinessTransactionException;
import it.dreamteam.balance.model.request.TransactionRequest;
import it.dreamteam.balance.model.response.TransactionResponse;
import it.dreamteam.balance.service.TransactionService;
import it.dreamteam.balance.util.TransactionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {
    Logger log = LoggerFactory.getLogger(this.getClass());
    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionService transactionService;

    @Test
    @DisplayName("Get transactions by date interval")
    public void getTransactionsByDateTest() {
        LocalDate startDate = LocalDate.EPOCH;
        LocalDate endDate = LocalDate.now();
        Map<String, LocalDate> requestMap = new HashMap<>();
        requestMap.put("startDate",startDate);
        requestMap.put("endDate", endDate);
        String url = "http://localhost:" +
                        port +
                        "/api/v1/transactions?" +
                        "startDate={startDate}&" +
                        "endDate={endDate}";

        ResponseEntity<TransactionResponse> response = restTemplate.getForEntity(
                url,
                TransactionResponse.class,
                requestMap
        );
        assertEquals(new ArrayList<>(), Objects.requireNonNull(response.getBody()).getResult());
    }

    @Test
    @DisplayName("Check exception")
    public void getCorrectException() {
        assertThrowsExactly(InvalidBusinessTransactionException.class, () -> transactionService.saveTransaction(null));
    }

    @Test
    @DisplayName("Check if transaction is successfully saved")
    public void insertTransactionSuccessfully() {
        TransactionRequest transaction = new TransactionRequest(TransactionType.IN, 200, LocalDate.now(), "affitto", "ignazio");
        assertDoesNotThrow(() -> transactionService.saveTransaction(transaction));
    }
}