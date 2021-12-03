package com.dbc.caixaapi.controller;

import com.dbc.caixaapi.dto.CaixaDTO;
import com.dbc.caixaapi.service.CaixaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/caixa")
@RequiredArgsConstructor
public class CaixaController {
    private final CaixaService caixaService;

    @GetMapping()
    public List<CaixaDTO> list(){
        return caixaService.list();
    }
}
