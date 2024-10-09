package com.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.DocRepresentanteLogEntity;

@Repository
public interface DocRepresentanteLogRepository extends JpaRepository<DocRepresentanteLogEntity, Long> {
}
