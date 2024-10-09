package com.fatec.tg.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doc_idoso_log")
public class DocIdosoLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_log")
    private long idLog;

    @Column(name = "id_docIDoso")
    private long idDocIdoso;

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

    public long getIdDocIdoso() {
        return idDocIdoso;
    }

    public void setIdDocIdoso(long idDocIdoso) {
        this.idDocIdoso = idDocIdoso;
    }
}
