package com.victorllunes.EventClean.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRespository extends JpaRepository<EventEntity, Long> {
}
