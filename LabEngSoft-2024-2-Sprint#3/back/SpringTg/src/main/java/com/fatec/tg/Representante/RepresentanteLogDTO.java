package com.fatec.tg.Representante;

import java.time.LocalDateTime;

public class RepresentanteLogDTO {

    private Long representanteId;
    private String acao;
    private LocalDateTime dataHora;

    // Getters e Setters
    public Long getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(Long representanteId) {
        this.representanteId = representanteId;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
