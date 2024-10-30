package com.fatec.tg.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "log_qrcode")
public class LogQRCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogQRCode;

    private String acao;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "id_qrcode", referencedColumnName = "idQRCode")
    private QRCodeEntity qrCode;

    
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

    public QRCodeEntity getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCodeEntity qrCode) {
        this.qrCode = qrCode;
    }
}
