package com.chugunoff.abtest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrencyResponse
{
    public Long timestamp;
    public String base;
    public Map<String, Double> rates;
}
