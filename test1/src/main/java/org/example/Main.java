package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> arrayMock = Arrays.asList("HTTP_302","HTTP_NO","MIME_ALL","abcde");

        String jsonContent = Files.readString(Path.of("src/main/resources/allegato1.json"));

        Gson gson = new Gson();
        JsonObject request = gson.fromJson(jsonContent, JsonObject.class);
        JsonObject response = ElaborationResponse.generateResponseGSON(request, arrayMock);

        System.out.println(response);

        //Check if the response is the same of the json file
        String jsonContent2 = Files.readString(Path.of("src/main/resources/allegato2.json"));
        JsonObject request2 = gson.fromJson(jsonContent2, JsonObject.class);
        System.out.println(request2.equals(response));



    }




}