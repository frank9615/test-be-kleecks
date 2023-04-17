package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rule {
    private Operation operation;
    private String data;
    private Integer positionStart;

    private Integer positionEnd;

    public Rule(Operation operation, String data, Integer positionStart) {
        this.operation = operation;
        this.data = data;
        this.positionStart = positionStart;
    }

    @Override
    public String toString() {
        if (positionEnd == null) {
            return operation + " " + data + " at " + positionStart;
        }
        return operation + " " + data + " from " + positionStart + " to " + positionEnd;
    }
}
