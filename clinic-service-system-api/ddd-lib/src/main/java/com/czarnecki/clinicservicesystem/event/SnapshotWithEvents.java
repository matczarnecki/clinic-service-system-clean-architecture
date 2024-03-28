package com.czarnecki.clinicservicesystem.event;

import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;

import java.util.List;

public interface SnapshotWithEvents<ID> extends EntitySnapshot<ID> {
    List<? extends DomainEvent> events();
}
