package com.chugunoff.abtest.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;


@JsonTest
@TestPropertySource("classpath:test.properties")
public class CurrencyTest {

    @Value("${currency.current}")
    String current;


    @Autowired
    JacksonTester<CurrencyResponse> currencyResponseTester;

    @Test
    public void testCurrencyResponse() throws IOException
    {
        CurrencyResponse currencyResponse = currencyResponseTester.parse( current ).getObject();
        Assertions.assertEquals( Double.valueOf(57.499902 ), currencyResponse.rates.get("RUB"));
        Assertions.assertEquals( Double.valueOf(1.0 ), currencyResponse.rates.get("USD"));
        Assertions.assertEquals( Double.valueOf(42350.0), currencyResponse.rates.get("IRR"));
    }

}
