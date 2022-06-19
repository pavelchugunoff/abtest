package com.chugunoff.abtest.controller;


import com.chugunoff.abtest.client.GifClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class GifControllerTest {

    @Value("${giphy.minus}")
    String broke;

    @Value("${giphy.plus}")
    String rich;

    @Value("$giphy.zero")
    String zero;

    @Value("${giphy.key}")
    String api;

    @Autowired GifController gifController;

    @MockBean private GifClient gifClient;

    @Test
    public void response_shWork() throws Exception
    {



        Assertions.assertEquals(true, testForContains(api,gifClient,"rich",gifController.response("ZAR")));
        Assertions.assertEquals(true, testForContains(api,gifClient,"broke",gifController.response("IRR")));
        Assertions.assertEquals(true, testForContains(api,gifClient,"zero",gifController.response("USD")));
    }

    public boolean testForContains(String api, GifClient gifClient, String search, String gif_url ) throws Exception
    {


        String ret = gifClient.getGif(api, search);
        Object obj = new JSONParser().parse(ret);
        JSONObject json = (JSONObject) obj;
        JSONArray json_data = (JSONArray) json.get("data");
        for (Object gif_obj : json_data)
        {
            JSONObject gif = (JSONObject) gif_obj;
            String embed_url = gif.get("embed_url").toString();
            if(embed_url == gif_url)
                return true;
        }
        return false;
    }


}
