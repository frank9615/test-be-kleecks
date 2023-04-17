package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Rule;
import org.example.service.RuleApplier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        //Read html file and rules
        String page1 = Files.readString(Path.of("src/main/resources/allegato3.html"));
        Document doc1 = Jsoup.parse(page1);

        String jsonContent = Files.readString(Path.of("src/main/resources/rules.json"));
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Rule>>(){}.getType();
        List<Rule> rules = gson.fromJson(jsonContent, listType);

        List<String> modifiedPage = RuleApplier.applyRule(rules, doc1);

        modifiedPage.forEach(System.out::println);

    }
}