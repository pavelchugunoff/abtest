package com.chugunoff.abtest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="gif-client", url="${giphy.url}")
public interface GifClient {
    @GetMapping()
    String getGif(@RequestParam("api_key") String api, @RequestParam("q") String gif);
}
