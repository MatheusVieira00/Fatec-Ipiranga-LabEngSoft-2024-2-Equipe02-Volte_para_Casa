package com.fatec.tg.DocIdoso;

import java.time.LocalDateTime;
public class DocIdosoLogDTO {
  
    private long idLog;

    private long idDocIdoso;

    private String acao;

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
