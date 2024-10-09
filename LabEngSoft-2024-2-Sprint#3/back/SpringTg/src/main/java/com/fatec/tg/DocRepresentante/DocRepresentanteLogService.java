package com.fatec.tg.DocRepresentante;



import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.tg.entity.DocRepresentanteLogEntity;
import com.fatec.tg.repository.DocRepresentanteLogRepository;

@Service
public class DocRepresentanteLogService {

    @Autowired
    private DocRepresentanteLogRepository logRepository;

    public void registrarAcao(Long idDocRepresentante, String acao) {
        DocRepresentanteLogEntity log = new DocRepresentanteLogEntity();
        log.setIdDocRepresentante(idDocRepresentante);
        log.setAcao(acao);
        log.setDataHora(LocalDateTime.now());

        logRepository.save(log);
    }
}
