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
    public static String text = "Joe Smith was born in California. " +
            "In 2017, he went to Paris, France in the summer. " +
            "His flight left at 3:00pm on July 10th, 2017. " +
            "After eating some escargot for the first time, Joe said, \"That was delicious!\" " +
            "He sent a postcard to his sister Jane Smith. " +
            "After hearing about Joe's trip, Jane decided she might go to France one day.";
    public static void main(String[] args) {
        // set up pipeline properties
        Properties props = new Properties();
        // set the list of annotators to run
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
        props.setProperty("coref.algorithm", "neural");
        // build pipeline
        Date before, after;
        before = new Date();
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        after = new Date();
        System.out.println(before + "\t" + after);
        System.out.println(after.compareTo(before));
        // create a document object
        CoreDocument document = new CoreDocument(text);
        before = new Date();
        System.out.println(after + "\t" + before);
        System.out.println(before.compareTo(after));
        // annnotate the document
        pipeline.annotate(document);
        after = new Date();
        System.out.println(before + "\t" + after);
        System.out.println(after.compareTo(before));
        // examples

        before = new Date();
        System.out.println("Get tokens and sentences and quotes and entities and corefChains");
        List<CoreLabel> tokens = document.tokens();
        List<CoreSentence> sentences = document.sentences();
        List<CoreQuote> quotes = document.quotes();
        List<CoreEntityMention> entities = document.entityMentions();
        Map<Integer, CorefChain> corefChains = document.corefChains();
        after = new Date();
        System.out.println(before + "\t" + after);
        System.out.println(after.compareTo(before));


        // tokens of the document
        System.out.println("All tokens");
        for (CoreLabel c: tokens) {
            System.out.print(c + "\t");
        }
        SpringApplication.run(NlpApplication.class, args);
    }

}
