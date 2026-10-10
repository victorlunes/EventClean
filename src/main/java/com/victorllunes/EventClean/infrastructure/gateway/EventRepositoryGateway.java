package com.victorllunes.EventClean.infrastructure.gateway;

import com.victorllunes.EventClean.core.entities.Event;
import com.victorllunes.EventClean.core.gateway.EventGateway;
import com.victorllunes.EventClean.infrastructure.mapper.event.EventEntityMapper;
import com.victorllunes.EventClean.infrastructure.persistence.EventEntity;
import com.victorllunes.EventClean.infrastructure.persistence.EventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * CAMADA: infrastructure — GATEWAY (implementação / "adapter").
 *
 * É a PONTE entre o core e o banco de dados. Implementa a interface
 * core/gateway/EventGateway usando JPA (EventRepository).
 *
 * O core chama eventGateway.createEvent(event) achando que fala com uma
 * interface qualquer; em tempo de execução, o Spring injetou ESTA classe.
 * Se um dia o banco mudar, você cria outra implementação de EventGateway
 * e o core nem percebe.
 *
 * @Component faz o Spring registrar esta classe como o bean de EventGateway,
 * que é usado pelo BeanConfiguration ao montar o CreateEventUseCaseImpl e o
 * ListEventsUseCaseImpl.
 *
 * Padrão de todo método daqui: domínio -> banco, chama o repositório,
 * banco -> domínio. O core só vê Event entrando e saindo.
 */
@Component
public class EventRepositoryGateway implements EventGateway {

    private final EventRepository eventRepository;
    private final EventEntityMapper eventEntityMapper;

    public EventRepositoryGateway(
            EventRepository repository,
            EventEntityMapper eventEntityMapper
    ) {
        this.eventRepository = repository;
        this.eventEntityMapper = eventEntityMapper;
    }

    @Override
    public Event createEvent(Event event) {
        // Passo 5: converte o objeto do domínio (Event) para o formato do banco (EventEntity).
        EventEntity eventEntity = eventEntityMapper.toEventEntity(event);
        // Passo 6: o JPA salva no banco e devolve a entidade já com o id gerado.
        EventEntity newEvent = eventRepository.save(eventEntity);
        // Passo 7: converte de volta para Event, pois o core não conhece EventEntity.
        return eventEntityMapper.toEvent(newEvent);
    }

    @Override
    public List<Event> findAll() {
        // (listar) Passo 4: o JPA faz um SELECT na tabela event e devolve
        // as linhas como EventEntity (formato do banco).
        List<EventEntity> events = eventRepository.findAll();

        // (listar) Passo 5: converte cada EventEntity em Event, pois o core
        // não conhece EventEntity.
        return eventEntityMapper.toEventList(events);
    }

    @Override
    public boolean existsByIdentificator(String identificator) {
        return eventRepository.existsByIdentificator(identificator);
    }

    @Override
    public Event findByIdentificator(String identificator) {
        return eventRepository.findEventByIdentificator(identificator);
    }
}
