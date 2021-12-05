package com.dbc.caixaapi.service;

import com.dbc.caixaapi.dto.CaixaDTO;
import com.dbc.caixaapi.dto.EmailDTO;
import com.dbc.caixaapi.entity.CaixaEntity;
import com.dbc.caixaapi.kafka.Producer;
import com.dbc.caixaapi.repository.CaixaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CaixaService {
    private final CaixaRepository caixaRepository;
    private final ObjectMapper objectMapper;
    private final Producer producer;

    public List<CaixaDTO> list(){
        List<CaixaEntity> listaEntity = caixaRepository.findAll();

        return listaEntity.stream()
                .map(x -> objectMapper.convertValue(x, CaixaDTO.class))
                .collect(Collectors.toList());
    }

    public CaixaDTO create(CaixaDTO caixaDTO) throws JsonProcessingException {
        CaixaEntity caixa = objectMapper.convertValue(caixaDTO, CaixaEntity.class);
        CaixaEntity caixaEntity = caixaRepository.insert(caixa);

        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dataCaixa = LocalDateTime.now();

        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        NumberFormat formatter = new DecimalFormat("#0.00");

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setAssunto("Valor total do caixa diário");
        emailDTO.setDestinatario("guiuser1999@gmail.com");
        emailDTO.setMensagem("O valor do caixa foi: R$ " + formatter.format(caixaEntity.getValorCaixa()));
        emailDTO.setData(caixaEntity.getDataCaixa());
        emailDTO.setNomeCliente("Guilherme");

        producer.sendCaixaDiario(emailDTO);
        return objectMapper.convertValue(caixaEntity, CaixaDTO.class);
    }

}
