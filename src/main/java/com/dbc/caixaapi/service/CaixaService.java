package com.dbc.caixaapi.service;

import com.dbc.caixaapi.dto.CaixaDTO;
import com.dbc.caixaapi.entity.CaixaEntity;
import com.dbc.caixaapi.repository.CaixaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaixaService {
    private final CaixaRepository caixaRepository;
    private final ObjectMapper objectMapper;

    public List<CaixaDTO> list(){
        List<CaixaEntity> listaEntity = caixaRepository.findAll();

        return listaEntity.stream()
                .map(x -> objectMapper.convertValue(x, CaixaDTO.class))
                .collect(Collectors.toList());
    }

    public CaixaDTO create(CaixaDTO caixaDTO){
        CaixaEntity caixa = objectMapper.convertValue(caixaDTO, CaixaEntity.class);
        CaixaEntity caixaEntity = caixaRepository.insert(caixa);
        return objectMapper.convertValue(caixaEntity, CaixaDTO.class);
    }

}
