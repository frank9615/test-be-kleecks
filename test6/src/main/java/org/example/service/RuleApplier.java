package org.example.service;

import org.example.model.Rule;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class RuleApplier {
    public static List<String> applyRule(List<Rule> rules, Document doc1) {
        List<String> page1 = new ArrayList<>(List.of(doc1.html().split("\\r?\\n")));
        System.out.println("Applying rule");
        
        //Per ogni regola presente nella lista delle regole applico la regola
        for(int i = 0 ; i < rules.size(); i++){
            Rule rule = rules.get(i);
            switch (rule.getOperation()){
                case INSERT:
                    page1.add(rule.getPositionStart(), rule.getData());
                    break;
                case REMOVE:
                    page1.remove(rule.getPositionStart());
                    break;
                case MOVE:
                    page1.remove(rule.getPositionStart());
                    page1.add(rule.getPositionEnd(), rule.getData());
                    break;
            }
        }
        return page1;
    }
}
