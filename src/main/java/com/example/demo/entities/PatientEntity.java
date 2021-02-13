package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patient")
//@NamedQueries(value =
//        {
//                @NamedQuery(name= PatientEntity.GET_ALL,
//                        query = "SELECT d FROM PatientEntity d"),
//                @NamedQuery(name= PatientEntity.GET_BY_ID,
//                        query = "SELECT d FROM PatientEntity d WHERE d.id = :id"),
//                @NamedQuery(name= PatientEntity.GET_BY_FIRSTNAME,
//                        query = "SELECT d FROM PatientEntity d WHERE d.first_name = :firstName"),
//                @NamedQuery(name= PatientEntity.GET_BY_LASTNAME,
//                        query = "SELECT d FROM PatientEntity d WHERE d.last_name = :lastName")
//        })
public class PatientEntity {

    public static final String GET_ALL = "PatientEntity.getAll";
    public static final String GET_BY_ID = "PatientEntity.getById";
    public static final String GET_BY_FIRSTNAME = "PatientEntity.getByFirstName";
    public static final String GET_BY_LASTNAME = "PatientEntity.getByLastName";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
//    private Integer diseases;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;


    @OneToMany(fetch=FetchType.EAGER)
    private List<DiseaseEntity> diseases;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    @Basic

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public List<DiseaseEntity> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<DiseaseEntity> diseases) {
        this.diseases = diseases;
    }
//    @Column(name = "diseases", nullable = true)
//    public Integer getDiseases() {
//        return diseases;
//    }
//
//    public void setDiseases(Integer diseases) {
//        this.diseases = diseases;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(diseases, that.diseases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, diseases);
    }
}
