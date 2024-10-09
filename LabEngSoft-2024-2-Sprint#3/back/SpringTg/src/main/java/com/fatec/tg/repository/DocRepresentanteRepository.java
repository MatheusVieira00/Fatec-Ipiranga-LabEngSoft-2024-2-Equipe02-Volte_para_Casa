package com.fatec.tg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.DocRepresentanteEntity;

@Repository
public interface DocRepresentanteRepository extends JpaRepository<DocRepresentanteEntity, Long> {
    List<DocRepresentanteEntity> findByRepresentanteId(Long representanteId);
}
