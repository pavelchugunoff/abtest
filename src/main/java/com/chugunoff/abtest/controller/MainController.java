package com.chugunoff.abtest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Controller
public class MainController
{

    @GetMapping("/{currency}")
    String main_post(@PathVariable("currency") String currency,  Model model) {
        model.addAttribute("title","Alfa bank test");

        String res = new RestTemplate().getForObject("http://localhost:8000/gif/"+currency,String.class, new HashMap<String,String>()).toString();

        if(res.contains("not_valid_data"))
        {
            model.addAttribute("currency","RUB");
            model.addAttribute("gif", "");
            model.addAttribute("error", "Invalid currency code!");
            return "index";
        }
        else {
            model.addAttribute("currency", currency);
            model.addAttribute("gif", res);
            model.addAttribute("error", "");
            return "gif";
        }

    }

    @GetMapping
    String main(Model model)
    {
        model.addAttribute("title","Alfa bank test");
        model.addAttribute("gif","");
        model.addAttribute("error", "");
        return "index";
    }




}
