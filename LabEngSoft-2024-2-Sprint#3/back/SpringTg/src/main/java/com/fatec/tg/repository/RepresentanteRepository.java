package com.fatec.tg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.RepresentanteEntity;


@Repository
public interface RepresentanteRepository extends JpaRepository<RepresentanteEntity, Long>, JpaSpecificationExecutor<RepresentanteEntity> {    
    Optional<RepresentanteEntity> findByEmail(String email);
    Optional<RepresentanteEntity> findByEmailAndIsDeletedFalse(String email);
    boolean existsByEmail(String email);
    List<RepresentanteEntity> findAllByIsDeletedFalse();

}

