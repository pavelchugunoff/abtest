package com.chugunoff.abtest.controller;


import com.chugunoff.abtest.client.CurrencyClient;
import com.chugunoff.abtest.response.CurrencyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CurrencyControllerTest {

    @Autowired CurrencyController currencyController;

    @MockBean
    CurrencyClient currencyClient;

    @Value("${currency.current}")
    String current;
    @Value("${currency.historical}")
    String historical;

    @Test
    public void response_shWork() throws Exception
    {

        ObjectMapper mapper = new ObjectMapper();

        CurrencyResponse currentCurrency = mapper.readValue(  current , CurrencyResponse.class );
        CurrencyResponse historicalCurrency = mapper.readValue(  historical , CurrencyResponse.class );

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Mockito.when( currencyClient.get_current_data().rates.get("RUB") ).thenReturn( currentCurrency.rates.get("RUB") );
        Mockito.when( currencyClient.get_historical_data(dateFormat.format(cal.getTime())).rates.get("RUB") ).thenReturn( historicalCurrency.rates.get("RUB") );

        Assertions.assertEquals(Double.valueOf(0.0), currencyController.response("USD"));
        Assertions.assertEquals(Double.valueOf(-1.0), currencyController.response("IRR"));
        Assertions.assertEquals(Double.valueOf(1.0), currencyController.response("ZAR"));
    }

}
