package com.darin.chatbot.nlp.pojo;

import lombok.Data;

import java.util.List;

@Data
public class NLPSentence {
    private List<NLPToken> tokens;

    public NLPSentence(List<NLPToken> tokens) {
        this.tokens = tokens;
    }
}
