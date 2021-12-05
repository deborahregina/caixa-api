package com.dbc.caixaapi.dto;

import lombok.Data;

@Data
public class EmailDTO {

    private String nomeCliente;
    private String mensagem;
    private String destinatario;
    private String assunto;
    private String data;

}
