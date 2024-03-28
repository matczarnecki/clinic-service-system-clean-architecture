package com.czarnecki.clinicservicesystem;

import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;

public interface Aggregate<ID, T extends EntitySnapshot<ID>> extends DomainEntity<ID, T> {
}
