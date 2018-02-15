package com.tracking.panel.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;

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
    @NotBlank
    @Column(nullable = false)
    @NotEmpty(message = "*Please provide postal code of Hospital")
    private String postalCode;
    @NotBlank
    @Column(nullable = false)
    @NotEmpty(message = "*Please provide address of Hospital")
    private String address;
    @NotBlank
    @Max(value = 8000)
    @Column(nullable = false,columnDefinition="VARCHAR(8000)")
    @NotEmpty(message = "*Please provide description of Hospital")
    private String aboutHospital;

    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private Set<HospitalsImages> hospitalsImages;
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private Set<HospitalEmploye> hospitalsEmployee;
    public Hospital() {
    }
    public Hospital(String fullName, String city, String postalCode, String address, String aboutHospital) {
        this.fullName = fullName;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
        this.aboutHospital = aboutHospital;
    }
    public Hospital(String fullName, String city, String postalCode, String address, String aboutHospital, Set<HospitalsImages> hospitalsImages, Set<HospitalEmploye> hospitalsEmployee) {
        this.fullName = fullName;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
        this.aboutHospital = aboutHospital;
        this.hospitalsImages = hospitalsImages;
        this.hospitalsEmployee = hospitalsEmployee;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAboutHospital() {
        return aboutHospital;
    }

    public void setAboutHospital(String aboutHospital) {
        this.aboutHospital = aboutHospital;
    }

    public Set<HospitalsImages> getHospitalsImages() {
        return hospitalsImages;
    }

    public void setHospitalsImages(Set<HospitalsImages> hospitalsImages) {
        this.hospitalsImages = hospitalsImages;
    }

    public Set<HospitalEmploye> getHospitalsEmployee() {
        return hospitalsEmployee;
    }

    public void setHospitalsEmployee(Set<HospitalEmploye> hospitalsEmployee) {
        this.hospitalsEmployee = hospitalsEmployee;
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
