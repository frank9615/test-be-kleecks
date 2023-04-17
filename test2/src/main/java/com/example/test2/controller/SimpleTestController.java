package com.example.test2.controller;

import com.example.test2.util.ElaborationResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SimpleTestController {

    @GetMapping("/test2")
    public ResponseEntity<ObjectNode> exampleMethod(@RequestParam List<String> stringList, @RequestBody ObjectNode jsonRequest) {
        ObjectNode response = ElaborationResponse.generateResponse(jsonRequest, stringList);
        return ResponseEntity.ok(response);
    }
}
