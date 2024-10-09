package com.fatec.tg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "localizacao")
public class QRCodeLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localizacao_id", nullable = false) 
    private Long idQRCodeLog;

    @ManyToOne
    @JoinColumn(name = "id_qrcode", referencedColumnName = "idQRCode")
    @JsonBackReference
    private QRCodeEntity qrCode;

    private String latitude;
    private String longitude;
    private String dataHora;

   
    public Long getIdQRCodeLog() {
        return idQRCodeLog;
    }

    public void setIdQRCodeLog(Long idQRCodeLog) {
        this.idQRCodeLog = idQRCodeLog;
    }

    public QRCodeEntity getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCodeEntity qrCode) {
        this.qrCode = qrCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
