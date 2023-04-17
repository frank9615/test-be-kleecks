package org.example.service;

import org.example.model.Operation;
import org.example.model.Rule;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RuleGenerator {
    
    public static List<Rule> generateRules2(Document doc1, Document doc2) {
        List<Rule> rules = new ArrayList<>();

        List<String> page1 = List.of(doc1.html().split("\\r?\\n"));
        List<String> page2 = List.of(doc2.html().split("\\r?\\n"));
        List<String> page1Copy = new ArrayList<>(page1);
        
        //Per ogni elemento presente nella pagina 2 verifico se è presente nella pagina 1
        //Se non è presente allora aggiungo una regola di tipo INSERT
        // e aggiungo l'elemento alla pagina 1
        //Aggiungo l'elemento perchè in questo modo modifico anche i nuovi indici degli elementi

        
        for(int i = 0 ; i < page2.size(); i++){
            String linePage2 = page2.get(i);
            if(!page1.contains(linePage2)){
                rules.add(new Rule(Operation.INSERT, linePage2, i));
                page1Copy.add(i, linePage2);
            }
        }
        // Per ogni elemento presente nella pagina 1 verifico se è presente nella pagina 2
        // Se la pagina2 contine l'elemento allora verifico la posizione degli elemnenti 
        //Se si trovano nella stessa posizione allora non eseguo nessuna operazione e proseguo con l'elemento successivo
        //altrimenti aggiungo una regola di tipo MOVE con l'indice di partenza della pagina 1 e l'indice di arrivo della pagina 2
        //che fanno riferimento al from e to della regola
        //Se invece la pagina 1 contiene un elemento non presente nella pagina 2 allora aggiungo una regola di tipo REMOVE
        page1.stream().iterator().forEachRemaining(linePage1 -> {
            if(page2.contains(linePage1)) {
                if(page1Copy.indexOf(linePage1) != page2.indexOf(linePage1)){
                    rules.add(new Rule(Operation.MOVE, linePage1, page1Copy.indexOf(linePage1), page2.indexOf(linePage1)));
                }
            }else{
                rules.add(new Rule(Operation.REMOVE, linePage1, page1Copy.indexOf(linePage1)));
                page1Copy.remove(page1Copy.indexOf(linePage1));
            }
        });

        return rules;
    }

}
