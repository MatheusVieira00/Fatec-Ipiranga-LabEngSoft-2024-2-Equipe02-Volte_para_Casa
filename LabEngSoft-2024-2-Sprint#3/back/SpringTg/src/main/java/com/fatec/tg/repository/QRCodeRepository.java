package com.fatec.tg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.QRCodeEntity;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCodeEntity, Long>, JpaSpecificationExecutor<QRCodeEntity> {

    List<QRCodeEntity> findByIdoso_IdIdoso(Long idIdoso);
}
