package com.czarnecki.clinicservicesystem.patient;

import com.czarnecki.clinicservicesystem.Aggregate;
import com.czarnecki.clinicservicesystem.patient.vo.PatientSnapshot;

class Patient implements Aggregate<Integer, PatientSnapshot> {
    static Patient from(final PatientSnapshot patientSnapshot) {
        return new Patient(patientSnapshot.id(), patientSnapshot.firstName(), patientSnapshot.lastName());
    }

    private Integer id;
    private String firstName;
    private String lastName;

    private Patient(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public PatientSnapshot getSnapshot() {
        return new PatientSnapshot(id, firstName, lastName);
    }
}
