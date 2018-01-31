package com.tracking.panel.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class HospitalsImages {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @Max(value = 255)
    private String path;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public HospitalsImages() {
    }

    public HospitalsImages(String path, Hospital hospital) {
        this.path = path;
        this.hospital = hospital;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "HospitalsImages{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", hospital=" + hospital +
                '}';
    }
}
