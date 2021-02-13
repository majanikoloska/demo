package com.example.demo.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "disease")@NamedQueries(value =
        {
                @NamedQuery(name= DiseaseEntity.GET_BY_PATIENT_ID,
                        query = "SELECT d FROM DiseaseEntity d WHERE d.diseaseId = :diseaseId")
        })
public class DiseaseEntity {

    public static final String GET_BY_PATIENT_ID = "DiseaseEntity.getByPatientId";

    private String diseaseId;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    @Basic
    @Column(name = "disease_id", nullable = false, length = -1)
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiseaseEntity that = (DiseaseEntity) o;
        return Objects.equals(diseaseId, that.diseaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId);
    }
}
