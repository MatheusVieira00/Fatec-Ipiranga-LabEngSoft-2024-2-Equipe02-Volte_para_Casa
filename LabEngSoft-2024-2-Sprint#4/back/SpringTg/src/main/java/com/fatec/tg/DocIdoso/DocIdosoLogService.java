package com.fatec.tg.DocIdoso;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.tg.entity.DocIdosoLogEntity;
import com.fatec.tg.repository.DocIdosoLogRepository;

@Service
public class DocIdosoLogService {

    @Autowired
    private DocIdosoLogRepository logRepository;

    public void registrarAcao(Long idDocIdoso, String acao) {
        DocIdosoLogEntity log = new DocIdosoLogEntity();
        log.setIdDocIdoso(idDocIdoso);
        log.setAcao(acao);
        log.setDataHora(LocalDateTime.now()); // Define a data e hora atuais

        logRepository.save(log);
    }
}
