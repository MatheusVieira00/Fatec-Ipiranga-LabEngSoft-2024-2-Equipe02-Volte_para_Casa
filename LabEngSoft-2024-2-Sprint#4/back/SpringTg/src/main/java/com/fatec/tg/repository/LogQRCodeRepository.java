package com.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.LogQRCodeEntity;

@Repository
public interface LogQRCodeRepository extends JpaRepository<LogQRCodeEntity, Long> {
}
