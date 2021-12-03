package com.dbc.caixaapi.service;


import com.dbc.caixaapi.dto.CaixaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConsumerService {
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final CaixaService caixaService;
    private final ObjectMapper objectMapper;


    @KafkaListener(
            topics = "${kafka.topic.caixa}",
            groupId = "${kafka.group-id}",
            containerFactory = "listenerContainerFactoryEarliest"
    )
    public void consumeSomaCaixa(@Payload String mensagem,
                           @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                           @Header(KafkaHeaders.OFFSET) Long offset) throws IOException, MessagingException, TemplateException {
        CaixaDTO caixaDTO = objectMapper.readValue(mensagem, CaixaDTO.class);
        log.info("MENSAGEM LIDA: '{}', CHAVE: '{}', OFFSET: '{}'", caixaDTO, key, offset);

        caixaService.create(caixaDTO);

    }

//    @KafkaListener(
//            topics = "${kafka.topic.pedidorealizado}",
//            groupId = "${kafka.group-id}",
//            containerFactory = "listenerContainerFactoryEarliest"
//    )
//    public void consumeEmailNovoPedido(@Payload String mensagem,
//                           @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
//                           @Header(KafkaHeaders.OFFSET) Long offset) throws IOException, MessagingException, TemplateException {
//        EmailDTO emailDTO = objectMapper.readValue(mensagem, EmailDTO.class);
//        log.info("MENSAGEM LIDA: '{}', CHAVE: '{}', OFFSET: '{}'", emailDTO, key, offset);
//        emailService.enviarEmailComTemplate(emailDTO);
//
//    }}
}