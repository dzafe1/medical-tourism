package com.tracking.panel.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    @NotEmpty(message = "*Please provide full name of Hospital")
    private String fullName;
    @NotBlank
    @Column(nullable = false)
    @NotEmpty(message = "*Please provide city of Hospital")
    private String city;
    @OneToMany(mappedBy = "hospital_id",cascade = CascadeType.ALL)
    private Set<HospitalsImages> hospitalsImages;
    @OneToMany(mappedBy = "hospital_id",cascade = CascadeType.ALL)
    private Set<HospitalEmploye> hospitalsEmployee;

    public Hospital() {
    }

    public Hospital(String fullName, String city, Set<HospitalsImages> hospitalsImages) {
        this.fullName = fullName;
        this.city = city;
        this.hospitalsImages = hospitalsImages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<HospitalsImages> getHospitalsImages() {
        return hospitalsImages;
    }

    public void setHospitalsImages(Set<HospitalsImages> hospitalsImages) {
        this.hospitalsImages = hospitalsImages;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
