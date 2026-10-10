package com.victorllunes.EventClean.core.useCases.findEventByIdentificator;

import com.victorllunes.EventClean.core.entities.Event;

public interface FindEventByIdentificatorUseCase {
    Event execute(String identificator);
}
