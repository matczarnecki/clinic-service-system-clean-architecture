package com.czarnecki.clinicservicesystem;

import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;

public interface DomainEntity<ID, T extends EntitySnapshot<ID>> {
    T getSnapshot();
}
