package com.victorllunes.EventClean.infrastructure.gateway;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.gateway.EventGateway;
import com.victorllunes.EventClean.infrastructure.mapper.event.EventEntityMapper;
import com.victorllunes.EventClean.infrastructure.persistence.EventEntity;
import com.victorllunes.EventClean.infrastructure.persistence.EventRespository;
import org.springframework.stereotype.Component;

/**
 * CAMADA: infrastructure — GATEWAY (implementação / "adapter").
 *
 * É a PONTE entre o core e o banco de dados. Implementa a interface
 * core/gateway/EventGateway usando JPA (EventRespository).
 *
 * O core chama eventGateway.createEvent(event) achando que fala com uma
 * interface qualquer; em tempo de execução, o Spring injetou ESTA classe.
 * Se um dia o banco mudar, você cria outra implementação de EventGateway
 * e o core nem percebe.
 *
 * @Component faz o Spring registrar esta classe como o bean de EventGateway,
 * que é usado pelo BeanConfiguration ao montar o CreateEventUseCaseImpl.
 */
@Component
public class EventRepositoryGateway implements EventGateway {

    private final EventRespository eventRespository;
    private final EventEntityMapper eventEntityMapper;

    public EventRepositoryGateway(
            EventRespository repository,
            EventEntityMapper eventEntityMapper
    ) {
        this.eventRespository = repository;
        this.eventEntityMapper = eventEntityMapper;
    }

    @Override
    public Event createEvent(Event event) {
        // Passo 5: converte o objeto do domínio (Event) para o formato do banco (EventEntity).
        EventEntity eventEntity = eventEntityMapper.toEventEntity(event);
        // Passo 6: o JPA salva no banco e devolve a entidade já com o id gerado.
        EventEntity newEvent = eventRespository.save(eventEntity);
        // Passo 7: converte de volta para Event, pois o core não conhece EventEntity.
        return eventEntityMapper.toEvent(newEvent);
    }
}
