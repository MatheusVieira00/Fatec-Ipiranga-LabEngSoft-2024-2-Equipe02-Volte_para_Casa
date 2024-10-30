package com.fatec.tg.QRCode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/qrcode")
public class QRCodeLogController {

    @Autowired
    private QRCodeLogService qrCodeLogService;

    @PostMapping("/coordenada/{id}")
    public ResponseEntity<QRCodeLogDTO> gravarCoordenada(
            @PathVariable Long id, 
            @RequestBody QRCodeLogDTO qrCodeLogDTO) {
        try {
            return qrCodeLogService.gravarCoordenada(id, qrCodeLogDTO);
        } catch (RuntimeException e) {
            QRCodeLogDTO erroDTO = new QRCodeLogDTO();
            erroDTO.setMensagem("Erro ao processar a requisição.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroDTO);
        }
    }

     
    @GetMapping("/coordenadas")
    public ResponseEntity<List<QRCodeLogDTO>> buscarCoordenadas(
            @RequestParam("id") Long id,
            @RequestParam(value = "dataInicio", required = false) String dataInicio,
            @RequestParam(value = "dataFim", required = false) String dataFim) {

        return qrCodeLogService.buscarCoordenadas(id, dataInicio, dataFim);
    }

}