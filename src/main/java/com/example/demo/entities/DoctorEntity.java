package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "doctor")
//@NamedQueries(value =
//        {
//                @NamedQuery(name= DoctorEntity.GET_ALL,
//                        query = "SELECT d FROM DoctorEntity d"),
//                @NamedQuery(name= DoctorEntity.GET_BY_ID,
//                        query = "SELECT d FROM DoctorEntity d WHERE d.id = :id"),
//                @NamedQuery(name= DoctorEntity.GET_BY_DEPARTMENT,
//                        query = "SELECT d FROM DoctorEntity d WHERE d.department = :department")
//        })
public class DoctorEntity {

    public static final String GET_ALL = "DoctorEntity.getAll";
    public static final String GET_BY_ID = "DoctorEntity.getById";
    public static final String GET_BY_DEPARTMENT = "DoctorEntity.getByDepartment";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String department;

    @OneToMany(fetch=FetchType.EAGER)
    private List<PatientEntity> patients;

    public List<PatientEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientEntity> patients) {
        this.patients = patients;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department", nullable = true, length = 255)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEntity that = (DoctorEntity) o;
        return id == that.id &&
                Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department);
    }
}
