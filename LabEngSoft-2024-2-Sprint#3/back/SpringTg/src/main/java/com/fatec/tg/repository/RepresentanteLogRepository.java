package com.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.RepresentanteLogEntity;

@Repository
public interface RepresentanteLogRepository extends JpaRepository<RepresentanteLogEntity, Long> {
}
