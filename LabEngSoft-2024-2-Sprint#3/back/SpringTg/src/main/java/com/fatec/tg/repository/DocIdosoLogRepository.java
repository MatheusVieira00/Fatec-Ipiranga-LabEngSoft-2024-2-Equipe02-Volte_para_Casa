package com.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.tg.entity.DocIdosoLogEntity;

@Repository
public interface DocIdosoLogRepository extends JpaRepository<DocIdosoLogEntity, Long> {
}
