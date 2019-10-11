package com.darin.chatbot.nlp.controller;

import com.darin.chatbot.nlp.pojo.NLPSentence;
import com.darin.chatbot.nlp.service.NLPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nlp")
public class NLPController {
    private NLPService nlpService;

    @Autowired
    public NLPController(NLPService nlpService) {
        this.nlpService = nlpService;
    }

    @RequestMapping(value = "/sentences", method = RequestMethod.POST)
    public List<String> getSentences(@RequestBody String text) {
        return nlpService.getSentences(text);
    }

    @RequestMapping(value = "/tokens", method = RequestMethod.POST)
    public List<String> getTokens(@RequestBody String text) {
        return nlpService.getTokens(text);
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public List<NLPSentence> getNLPResult(@RequestBody String text) {
        return nlpService.getNLPResult(text);
    }
}
