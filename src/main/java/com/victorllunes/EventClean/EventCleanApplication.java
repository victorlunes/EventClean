package com.victorllunes.EventClean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicação Spring Boot.
 *
 * Visão geral da Arquitetura Limpa neste projeto:
 *
 *   core/            -> REGRAS DE NEGÓCIO. Não conhece Spring, JPA, HTTP nem banco.
 *     entities/        o que é um "Event" para o negócio
 *     enums/           valores do domínio (tipos de evento)
 *     useCases/        o que o sistema FAZ (ex.: criar evento)
 *     gateway/         "portas": interfaces que o core precisa que alguém implemente
 *
 *   infrastructure/  -> DETALHES TÉCNICOS. Conhece o core, mas o core não conhece ela.
 *     presentation/    entrada HTTP (controllers)
 *     dtos/            formato dos dados que entram/saem pela API
 *     mapper/          conversores entre os formatos de cada camada
 *     gateway/         implementação das portas do core (adapters)
 *     persistence/     JPA: entidade de banco e repositório
 *     beans/           ensina o Spring a montar as classes do core
 *
 * Regra de ouro: as dependências sempre apontam PARA DENTRO (infrastructure -> core).
 */
@SpringBootApplication
public class EventCleanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventCleanApplication.class, args);
	}

}
