package com.darin.chatbot.nlp;

import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableDiscoveryClient
public class NlpApplication {
    public static void main(String[] args) {
        SpringApplication.run(NlpApplication.class, args);
    }

}
