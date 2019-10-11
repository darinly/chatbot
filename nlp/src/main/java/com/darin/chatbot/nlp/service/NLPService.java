package com.darin.chatbot.nlp.service;

import com.darin.chatbot.nlp.pojo.NLPSentence;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NLPService {
    List<String> getSentences(String text);
    List<String> getTokens(String text);
    List<NLPSentence> getNLPResult(String text);
}
