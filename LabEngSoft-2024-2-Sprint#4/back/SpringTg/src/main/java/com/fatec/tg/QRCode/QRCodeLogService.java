

package com.fatec.tg.QRCode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fatec.tg.entity.QRCodeEntity;
import com.fatec.tg.entity.QRCodeLogEntity;
import com.fatec.tg.repository.QRCodeLogRepository;
import com.fatec.tg.repository.QRCodeRepository;

@Service
public class QRCodeLogService {

    @Autowired
    private QRCodeLogRepository qrCodeLogRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    public ResponseEntity<QRCodeLogDTO> gravarCoordenada(Long id, QRCodeLogDTO qrCodeLogDTO) {
       
        List<QRCodeEntity> qrCodes = qrCodeRepository.findByIdoso_IdIdoso(id);

        if (qrCodes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        QRCodeEntity qrCode = qrCodes.get(0);

        
        String latitude = qrCodeLogDTO.getLatitude();
        String longitude = qrCodeLogDTO.getLongitude();

        
        if (latitude == null || longitude == null) {
            QRCodeLogDTO erroDTO = new QRCodeLogDTO();
            erroDTO.setMensagem("Latitude e longitude são obrigatórias.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDTO);
        }

        
        QRCodeLogEntity qrCodeLogEntity = new QRCodeLogEntity();
        qrCodeLogEntity.setQrCode(qrCode);
        qrCodeLogEntity.setLatitude(latitude);
        qrCodeLogEntity.setLongitude(longitude);

        
        LocalDateTime agora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataHoraFormatada = agora.format(formatter);
        qrCodeLogEntity.setDataHora(dataHoraFormatada);

        
        qrCodeLogRepository.save(qrCodeLogEntity);

        
        QRCodeLogDTO respostaDTO = new QRCodeLogDTO();
        respostaDTO.setMensagem("Informações gravadas com sucesso.");
        respostaDTO.setDataHora(dataHoraFormatada);
        respostaDTO.setLatitude(latitude);
        respostaDTO.setLongitude(longitude);

        return ResponseEntity.status(HttpStatus.CREATED).body(respostaDTO);
    }

    public ResponseEntity<List<QRCodeLogDTO>> buscarCoordenadas(Long id, String dataInicio, String dataFim) {

        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        
        LocalDateTime dataFinal = (dataFim == null) 
                ? LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
                : LocalDateTime.parse(dataFim + " 23:59:59", formatter);

        
        LocalDateTime dataInicial = (dataInicio == null) 
                ? dataFinal.minus(30, ChronoUnit.DAYS)
                : LocalDateTime.parse(dataInicio + " 00:00:00", formatter);

        
        List<QRCodeEntity> qrCodes = qrCodeRepository.findByIdoso_IdIdoso(id);
        if (qrCodes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        
        QRCodeEntity qrCode = qrCodes.get(0);

        
        List<QRCodeLogEntity> qrCodeLogs = qrCodeLogRepository.findByQrCodeIdQRCodeAndDataHoraBetween(
                qrCode.getIdQRCode(), dataInicial.format(formatter), dataFinal.format(formatter));

        
        List<QRCodeLogDTO> resultado = qrCodeLogs.stream().map(log -> {
            QRCodeLogDTO dto = new QRCodeLogDTO();
            dto.setLatitude(log.getLatitude());
            dto.setLongitude(log.getLongitude());
            dto.setDataHora(log.getDataHora());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }
}

