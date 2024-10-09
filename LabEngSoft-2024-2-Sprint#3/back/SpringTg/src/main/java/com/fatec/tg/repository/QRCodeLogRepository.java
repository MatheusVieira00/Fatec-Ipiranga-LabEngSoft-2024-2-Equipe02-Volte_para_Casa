

package com.fatec.tg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.QRCodeLogEntity;

@Repository
public interface QRCodeLogRepository extends JpaRepository<QRCodeLogEntity, Long> {

    
    List<QRCodeLogEntity> findByQrCodeIdQRCode(Long idQRCode);

    List<QRCodeLogEntity> findByQrCodeIdQRCodeAndDataHoraBetween(Long idQRCode, String dataInicio, String dataFim);
}
