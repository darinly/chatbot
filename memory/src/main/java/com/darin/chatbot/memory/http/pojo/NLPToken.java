package com.darin.chatbot.memory.http.pojo;

import lombok.Data;

@Data
class NLPToken {
    private String token;
    private String posTag;
    private String nerTag;
}
