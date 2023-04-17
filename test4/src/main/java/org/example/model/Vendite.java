package org.example.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendite {
    protected String user;
    protected int anno;
    protected BigDecimal totale;

}
