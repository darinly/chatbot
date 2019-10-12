package com.darin.chatbot.memory.http;

import org.springframework.web.client.RestTemplate;

public class HttpClient {
    private RestTemplate restTemplate;

    public HttpClient() {
        restTemplate = new RestTemplate();
    }
}
