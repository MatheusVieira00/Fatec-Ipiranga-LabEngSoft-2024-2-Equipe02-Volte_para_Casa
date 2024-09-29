package com.fatec.tg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.RepresentanteEntity;


@Repository
public interface RepresentanteRepository extends JpaRepository<RepresentanteEntity, Long>, 
JpaSpecificationExecutor<RepresentanteEntity> {    
    // List<RepresentanteEntity> findByEmail(String email);
    Optional<RepresentanteEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}