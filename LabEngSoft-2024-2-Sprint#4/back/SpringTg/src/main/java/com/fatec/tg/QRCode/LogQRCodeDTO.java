package com.fatec.tg.QRCode;

import java.time.LocalDateTime;

public class LogQRCodeDTO {
    private Long idLogQRCode;
    private String acao;
    private LocalDateTime dataHora;
    private Long idQRCode;

    public LogQRCodeDTO(Long idLogQRCode, String acao, LocalDateTime dataHora, Long idQRCode) {
        this.idLogQRCode = idLogQRCode;
        this.acao = acao;
        this.dataHora = dataHora;
        this.idQRCode = idQRCode;
    }

    
    public Long getIdLogQRCode() {
        return idLogQRCode;
    }

    public void setIdLogQRCode(Long idLogQRCode) {
        this.idLogQRCode = idLogQRCode;
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

    public Long getIdQRCode() {
        return idQRCode;
    }

    public void setIdQRCode(Long idQRCode) {
        this.idQRCode = idQRCode;
    }
}
