package com.example.test2.util;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

import static java.util.Objects.isNull;


public class ElaborationResponse {

    public static ObjectNode generateResponse(ObjectNode request, List<String> labels) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode response = mapper.createObjectNode();
        labels.forEach( label -> {
            ArrayNode crawlIdResponseList = mapper.createArrayNode();
            //get keyset of request
            request.fieldNames().forEachRemaining( crawlId -> {
                //Get ObjectNode of crawlId
                JsonNode crawlIdAll = request.get(crawlId).get("ALL");
                if(!isNull(crawlIdAll) && crawlIdAll.has(label)){
                    ObjectNode crawlLabelResponse =mapper.createObjectNode();
                    crawlLabelResponse.put("crawlId", crawlId);
                    crawlLabelResponse.put("total", crawlIdAll.get(label).longValue());
                    crawlLabelResponse.put("totalInt", crawlIdAll.get("INT-" + label).longValue());
                    crawlLabelResponse.put("totalExt", crawlIdAll.get("EXT-" + label).longValue());
                    crawlIdResponseList.add(crawlLabelResponse);
                }
            });
            if(crawlIdResponseList.size() > 0) {
                response.put(label, crawlIdResponseList);
            }
        });
        return response;
    }





}
