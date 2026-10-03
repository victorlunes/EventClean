package com.victorllunes.EventClean.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CAMADA: infrastructure — REPOSITÓRIO (Spring Data JPA).
 *
 * Ao estender JpaRepository, o Spring gera em tempo de execução a implementação
 * de save, findById, findAll, delete etc. para EventEntity (com id do tipo Long).
 *
 * Só é usado pelo EventRepositoryGateway. O core nunca fala direto com o
 * repositório — ele fala com a interface EventGateway.
 */
public interface EventRespository extends JpaRepository<EventEntity, Long> {
}
