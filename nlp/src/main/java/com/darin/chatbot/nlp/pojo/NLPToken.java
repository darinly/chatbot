package com.darin.chatbot.nlp.pojo;

import lombok.Data;

@Data
public class NLPToken {
    private String token;
    private String posTag;
    private String nerTag;

    public NLPToken(String token, String posTag, String nerTag) {
        this.token = token;
        this.posTag = posTag;
        this.nerTag = nerTag;
    }
}
