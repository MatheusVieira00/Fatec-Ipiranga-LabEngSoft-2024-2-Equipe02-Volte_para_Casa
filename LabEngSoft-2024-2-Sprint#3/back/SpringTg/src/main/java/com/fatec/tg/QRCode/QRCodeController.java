package com.fatec.tg.QRCode;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/QRCode/idoso/{id}")
    public ResponseEntity<QRCodeDTO> getQRCode(@PathVariable long id) {
        try {
            QRCodeDTO response = qrCodeService.generateQRCodeForIdoso(id);
            return ResponseEntity.ok(response);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/api/qrcode/idoso/{id}")
    public ResponseEntity<QRCodeDTO> buscarIdosoPorId(@PathVariable long id) {
        QRCodeDTO response = qrCodeService.getQRCodeForIdoso(id);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }
}
