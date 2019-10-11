package com.darin.chatbot.nlp.service.impl;

import com.darin.chatbot.nlp.pojo.NLPSentence;
import com.darin.chatbot.nlp.pojo.NLPToken;
import com.darin.chatbot.nlp.service.NLPService;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StanfordNLPService implements NLPService {
    private StanfordCoreNLP pipeline;
    private LRU<String, CoreDocument> lru;

    private static class LRU<K, V> extends LinkedHashMap<K, V> implements Map<K, V> {
        private int maxCapacity;

        LRU(int initialCapacity,
            float loadFactor,
            boolean accessOrder,
            int maxCapacity) {
            super(initialCapacity, loadFactor, accessOrder);
            this.maxCapacity = maxCapacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
            return size() > maxCapacity;
        }
    }

    public StanfordNLPService() {
        lru = new LRU<>(256, 0.95f, true, 240);

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        props.setProperty("coref.algorithm", "neural");
        pipeline = new StanfordCoreNLP(props);
    }

    private CoreDocument getDoc(String text) {
        if (lru.containsKey(text)) {
            return lru.get(text);
        }
        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);
        lru.put(text, doc);
        return doc;
    }

    public List<String> getSentences(String text) {
        CoreDocument doc = getDoc(text);
        List<String> sentences = new ArrayList<>();
        for (CoreSentence s: doc.sentences()) {
            sentences.add(s.text());
        }
        return sentences;
    }

    public List<String> getTokens(String text) {
        CoreDocument doc = getDoc(text);
        List<String> tokens = new ArrayList<>();
        for (CoreLabel l: doc.tokens()) {
            tokens.add(l.value());
        }
        return tokens;
    }

    public List<NLPSentence> getNLPResult(String text) {
        CoreDocument doc = getDoc(text);
        List<CoreSentence> sentences = doc.sentences();
        List<NLPSentence> info = new ArrayList<>();
        for (CoreSentence cs: sentences) {
            List<CoreLabel> tokens = cs.tokens();
            List<NLPToken> parsedTokens = new ArrayList<>();
            for (CoreLabel l: tokens) {
                NLPToken t = new NLPToken(l.word(), l.tag(), l.ner());
                parsedTokens.add(t);
            }
            NLPSentence s = new NLPSentence(parsedTokens);
            info.add(s);
        }
        return info;
    }

}
