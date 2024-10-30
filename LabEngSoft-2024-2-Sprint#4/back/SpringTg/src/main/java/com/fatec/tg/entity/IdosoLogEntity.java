package com.fatec.tg.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "idoso_log")
public class IdosoLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_log")
    private long idLog;

    @Column(name = "id_representante")
    private long idRepresentante;

    @Column(name = "acao")
    private String acao;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    

    public long getIdLog() {
        return idLog;
    }

    public void setIdLog(long idLog) {
        this.idLog = idLog;
    }

    public long getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(long idRepresentante) {
        this.idRepresentante = idRepresentante;
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
