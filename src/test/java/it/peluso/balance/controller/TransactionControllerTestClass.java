package it.peluso.balance.controller;

import it.peluso.balance.model.response.TransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTestClass {
    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Get transactions by date interval")
    public void getTransactionsByDateTest() throws Exception {
        LocalDate startDate = LocalDate.EPOCH;
        LocalDate endDate = LocalDate.now();
        Map<String, LocalDate> requestMap = new HashMap<>();
        requestMap.put("startDate",startDate);
        requestMap.put("endDate", endDate);
        URL url = new URL("http://localhost:" +
                        port +
                        "/api/v1/transactions?" +
                        "startDate={startDate}&" +
                        "endDate={endDate}"
                );

        ResponseEntity<TransactionResponse> response = restTemplate.getForEntity(
                url.toString(),
                TransactionResponse.class,
                requestMap
        );
        Assertions.assertEquals(new ArrayList<>(), Objects.requireNonNull(response.getBody()).getResult());
    }

}