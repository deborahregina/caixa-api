package com.dbc.caixaapi.entity;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(value = "caixa-diario")
public class CaixaEntity {

    @Id
    private String id;
    private BigDecimal valorCaixa;
    private LocalDateTime dataCaixa;
}
