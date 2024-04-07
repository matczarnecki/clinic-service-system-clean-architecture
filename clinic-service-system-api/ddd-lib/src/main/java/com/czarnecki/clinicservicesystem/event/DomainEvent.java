package com.czarnecki.clinicservicesystem.event;

import java.time.Instant;

public interface DomainEvent {
    Instant occurredOn();
}
