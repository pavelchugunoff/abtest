package com.chugunoff.abtest.controller;

import com.chugunoff.abtest.client.CurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class CurrencyController
{

    CurrencyClient currencyClient;

    @Autowired
    public CurrencyController(CurrencyClient currencyClient)
    {
        this.currencyClient = currencyClient;
    }

    @ResponseBody
    @GetMapping("/currency/{currency}")
    String response(@PathVariable("currency") String currency)
    {

        if(currencyClient.get_current_data().rates.get(currency) == null)
        {
            return "not_valid_data";
        }
        else
        {
            final Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            double current = currencyClient.get_current_data().rates.get(currency);
            double historical = currencyClient.get_historical_data(dateFormat.format(cal.getTime())).rates.get(currency);

            int ret = 0;

            if (current > historical)
                ret = 1;
            else if (current < historical)
                ret = -1;

            return Integer.toString(ret);
        }
    }


}
