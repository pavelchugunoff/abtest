package com.chugunoff.abtest.client;

import com.chugunoff.abtest.response.CurrencyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-client", url="${currency.url}")
public interface CurrencyClient
{
    @GetMapping("/latest.json?app_id=${currency.key}&base=${currency.base}")
    CurrencyResponse get_current_data();

    @GetMapping("/historical/{date}.json?app_id=${currency.key}")
    CurrencyResponse get_historical_data(@PathVariable("date") String date);
}
