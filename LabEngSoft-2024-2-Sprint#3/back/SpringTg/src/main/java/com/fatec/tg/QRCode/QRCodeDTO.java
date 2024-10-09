package com.fatec.tg.QRCode;

public class QRCodeDTO {
    private Long idQRCode;
    private String qrCode;
    private Long idIdoso; 

    public QRCodeDTO() {
        
    }

    public QRCodeDTO(Long idQRCode, String qrCode, Long idIdoso) {
        this.idQRCode = idQRCode;
        this.qrCode = qrCode;
        this.idIdoso = idIdoso;
    }

    public Long getIdQRCode() {
        return idQRCode;
    }

    public void setIdQRCode(Long idQRCode) {
        this.idQRCode = idQRCode;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Long getIdIdoso() {
        return idIdoso;
    }

    public void setIdIdoso(Long idIdoso) {
        this.idIdoso = idIdoso;
    }
}
