package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;
import static java.util.Objects.isNull;


public class ElaborationResponse {

    public static JsonObject generateResponseGSON(JsonObject request, List<String> labels) {
        JsonObject response = new JsonObject();
        //Per ogni label presente nella lista verifico se è presente nel json di input sotto la chiave "ALL"
        // di ogni oggetto 
        // Se è presente allora leggo il valore della label e lo aggingo al json di output
        labels.forEach( label -> {
            JsonArray crawlIdResponseList = new JsonArray();
            request.keySet().forEach( crawlId -> {
                JsonObject crawlIdAll = request.getAsJsonObject(crawlId).getAsJsonObject("ALL");
                if(!isNull(crawlIdAll) && crawlIdAll.has(label)){
                    JsonObject crawlLabelResponse = new JsonObject();
                    crawlLabelResponse.addProperty("crawlId", crawlId);
                    crawlLabelResponse.addProperty("total", crawlIdAll.get(label).getAsLong());
                    crawlLabelResponse.addProperty("totalInt", crawlIdAll.get("INT-" + label).getAsLong());
                    crawlLabelResponse.addProperty("totalExt", crawlIdAll.get("EXT-" + label).getAsLong());
                    crawlIdResponseList.add(crawlLabelResponse);
                }
            });
            if(crawlIdResponseList.size() > 0) {
                response.add(label, crawlIdResponseList);
            }
        });
        return response;
    }

}
