package com.czarnecki.clinicservicesystem.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
