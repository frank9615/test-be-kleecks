package org.example.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class VenditeVariazione extends Vendite{
    private BigDecimal variazione;

    //to string method
    @Override
    public String toString() {
        return "VenditeVariazione{" +
                "user='" + user + '\'' +
                ", anno=" + anno +
                ", totale=" + totale +
                ", variazione=" + variazione +
                '}';
    }
}
