package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;
import org.example.model.Rule;
import org.example.service.RuleGenerator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.isNull;

public class Main {
    public static void main(String[] args) throws IOException {
        String page1 = Files.readString(Path.of("src/main/resources/allegato3.html"));
        String page2 = Files.readString(Path.of("src/main/resources/allegato4.html"));
        Document doc1 = Jsoup.parse(page1);
        Document doc2 = Jsoup.parse(page2);

        List<Rule> rules = RuleGenerator.generateRules2(doc1, doc2);

        if(!isNull(rules)) {
            rules.forEach(System.out::println);
        }
        //Return a json object
        JsonArray response = new Gson().toJsonTree(rules).getAsJsonArray();

        FileWriter file = new FileWriter("output.json");
        Gson gson = new Gson();
        String jsonString = gson.toJson(response);
        file.write(jsonString);
        file.close();


    }

}