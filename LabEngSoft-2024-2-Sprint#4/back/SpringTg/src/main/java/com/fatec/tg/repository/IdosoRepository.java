package com.fatec.tg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.IdosoEntity;
import com.fatec.tg.entity.RepresentanteEntity;

@Repository
public interface IdosoRepository extends JpaRepository<IdosoEntity, Long> {

    Optional<IdosoEntity> findByIdIdosoAndIsDeletedFalse(Long idIdoso);

    List<IdosoEntity> findAllByIsDeletedFalse();

    // Optional<IdosoEntity> findByRepresentante(RepresentanteEntity representante);

    List<IdosoEntity> findByRepresentanteAndIsDeletedFalse(RepresentanteEntity representante);

  
}
