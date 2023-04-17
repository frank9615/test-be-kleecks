package org.example.service;

import org.example.model.Operation;
import org.example.model.Rule;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleGenerator {

    public static List<Rule> generateRules(Document doc1, Document doc2) {
        List<Rule> rules = new ArrayList<>();

        List<String> page1 = List.of(doc1.html().split("\\r?\\n"));
        List<String> page2 = List.of(doc2.html().split("\\r?\\n"));

        for(int i = 0 ; i < page2.size(); i++){
            String linePage2 = page2.get(i);
            if(!page1.contains(linePage2)){
                rules.add(new Rule(Operation.INSERT, linePage2, i));
            }
        }

        page1.stream().iterator().forEachRemaining(linePage1 -> {
            if(page2.contains(linePage1)) {
                if(page1.indexOf(linePage1) != page2.indexOf(linePage1)){
                    rules.add(new Rule(Operation.MOVE, linePage1, page1.indexOf(linePage1), page2.indexOf(linePage1)));
                }
            }else{
                rules.add(new Rule(Operation.REMOVE, linePage1, page1.indexOf(linePage1)));
            }
        });

        return rules;
    }

    public static List<Rule> generateRules2(Document doc1, Document doc2) {
        List<Rule> rules = new ArrayList<>();

        List<String> page1 = List.of(doc1.html().split("\\r?\\n"));
        List<String> page2 = List.of(doc2.html().split("\\r?\\n"));
        List<String> page1Copy = new ArrayList<>(page1);

        for(int i = 0 ; i < page2.size(); i++){
            String linePage2 = page2.get(i);
            if(!page1.contains(linePage2)){
                rules.add(new Rule(Operation.INSERT, linePage2, i));
                page1Copy.add(i, linePage2);
            }
        }

        page1.stream().iterator().forEachRemaining(linePage1 -> {
            if(page2.contains(linePage1)) {
                if(page1Copy.indexOf(linePage1) != page2.indexOf(linePage1)){
                    rules.add(new Rule(Operation.MOVE, linePage1, page1Copy.indexOf(linePage1), page2.indexOf(linePage1)));
                }
            }else{
                rules.add(new Rule(Operation.REMOVE, linePage1, page1Copy.indexOf(linePage1)));
            }
        });

        return rules;
    }

}
