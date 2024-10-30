package com.fatec.tg.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "qrcode")
public class QRCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idQRCode;

    @Lob
    private String qrCode;

    @OneToOne
    @JoinColumn(name = "id_idoso", referencedColumnName = "id_idoso", unique = true)
    @JsonBackReference
    private IdosoEntity idoso;

    @OneToMany(mappedBy = "qrCode")
    @JsonManagedReference
    private List<QRCodeLogEntity> qrCodeLogs;

   
    public IdosoEntity getIdoso() {
        return idoso;
    }

    public void setIdoso(IdosoEntity idoso) {
        this.idoso = idoso;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Long getIdQRCode() {
        return idQRCode;
    }

    public void setIdQRCode(Long idQRCode) {
        this.idQRCode = idQRCode;
    }

    public List<QRCodeLogEntity> getQrCodeLogs() {
        return qrCodeLogs;
    }

    public void setQrCodeLogs(List<QRCodeLogEntity> qrCodeLogs) {
        this.qrCodeLogs = qrCodeLogs;
    }
}

