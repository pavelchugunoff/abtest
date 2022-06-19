package com.chugunoff.abtest.controller;

import com.chugunoff.abtest.client.GifClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class GifController
{

    GifClient gifClient;

    @Value("${giphy.zero}")
    String gif_zero;
    @Value("${giphy.minus}")
    String gif_minus;
    @Value("${giphy.plus}")
    String gif_plus;
    @Value("${giphy.key}")
    String api;
    @Value("${test.currency.url}")
    String currency_url;


    @Autowired
    public GifController(GifClient gifClient)
    {
        this.gifClient = gifClient;
    }

    @ResponseBody
    @GetMapping("/gif/{currency}")
    String response(@PathVariable("currency") String currency) throws ParseException
    {
        String res = new RestTemplate().getForObject(currency_url+currency,String.class, new HashMap<String,String>()).toString();
        boolean valid = true;
        String gif_srch = "";

        switch (res)
        {
            case "-1":
                gif_srch = gif_minus;
                break;
            case "1":
                gif_srch = gif_plus;
                break;
            case "0":
                gif_srch = gif_zero;
                break;
            default:
                valid = false;
                break;
        }

        if(!valid)
        {
            return res;
        }
        else
        {
            String ret = gifClient.getGif(api, gif_srch);
            Object obj = new JSONParser().parse(ret);
            JSONObject json = (JSONObject) obj;
            JSONArray json_data = (JSONArray) json.get("data");
            String[] gifs = new String[json_data.size()];
            int i = 0;
            for (Object gif_obj : json_data)
            {
                JSONObject gif = (JSONObject) gif_obj;
                gifs[i] = gif.get("embed_url").toString();
                i++;
            }

            return gifs[ThreadLocalRandom.current().nextInt(0, 50)];
        }
    }

}
