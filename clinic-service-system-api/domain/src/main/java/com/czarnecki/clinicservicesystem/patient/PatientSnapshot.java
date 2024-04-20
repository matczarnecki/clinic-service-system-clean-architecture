package com.czarnecki.clinicservicesystem.patient;

import com.czarnecki.clinicservicesystem.vo.EntitySnapshot;

public record PatientSnapshot(Integer id, String firstName, String lastName)
    implements EntitySnapshot<Integer> {

}
