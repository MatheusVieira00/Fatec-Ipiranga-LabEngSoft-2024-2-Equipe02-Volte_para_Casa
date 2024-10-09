package com.fatec.tg.QRCode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.tg.entity.IdosoEntity;
import com.fatec.tg.entity.LogQRCodeEntity;
import com.fatec.tg.entity.QRCodeEntity;
import com.fatec.tg.repository.IdosoRepository;
import com.fatec.tg.repository.LogQRCodeRepository;
import com.fatec.tg.repository.QRCodeRepository;
import com.google.zxing.WriterException;

@Service
public class QRCodeService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private IdosoRepository idosoRepository;

    @Autowired
    private LogQRCodeRepository logQRCodeRepository;

    public QRCodeDTO generateQRCodeForIdoso(long id) throws WriterException, IOException {
        List<QRCodeEntity> qrCodes = qrCodeRepository.findByIdoso_IdIdoso(id);

        if (qrCodes.isEmpty()) {
            IdosoEntity idoso = idosoRepository.findById(id).orElseThrow(() -> new RuntimeException("Idoso não encontrado"));
            String urlIdoso = "http://localhost:4200/info-idoso/" + id;

            byte[] image = QRCodeGenerator.getQRCodeImage(urlIdoso, 250, 250);
            String qrcodeBase64 = Base64.getEncoder().encodeToString(image);

            QRCodeEntity entity = new QRCodeEntity();
            entity.setIdoso(idoso);
            entity.setQrCode(qrcodeBase64);
            qrCodeRepository.save(entity);

            logQRCodeAction(entity, "QRCode gerado");

            return new QRCodeDTO(entity.getIdQRCode(), qrcodeBase64, idoso.getIdIdoso());
        } else {
            QRCodeEntity qrCode = qrCodes.get(0);
            logQRCodeAction(qrCode, "QRCode buscado");
            return new QRCodeDTO(qrCode.getIdQRCode(), qrCode.getQrCode(), qrCode.getIdoso().getIdIdoso());
        }
    }

    public QRCodeDTO getQRCodeForIdoso(long id) {
        List<QRCodeEntity> qrCodes = qrCodeRepository.findByIdoso_IdIdoso(id);

        if (qrCodes.isEmpty()) {
            return null;
        } else {
            QRCodeEntity qrCode = qrCodes.get(0);
            logQRCodeAction(qrCode, "QRCode lido");
            return new QRCodeDTO(qrCode.getIdQRCode(), qrCode.getQrCode(), qrCode.getIdoso().getIdIdoso());
        }
    }

    private void logQRCodeAction(QRCodeEntity qrCodeEntity, String acao) {
        LogQRCodeEntity log = new LogQRCodeEntity();
        log.setQrCode(qrCodeEntity);
        log.setAcao(acao);
        log.setDataHora(LocalDateTime.now()); 

        logQRCodeRepository.save(log); 
    }
}
