package com.dbc.caixaapi.dto;


import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CaixaDTO {
    private String id;
    private BigDecimal valorCaixa;
    private String dataCaixa;
}
