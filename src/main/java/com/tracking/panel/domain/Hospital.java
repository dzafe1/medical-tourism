package com.tracking.panel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Where(clause="is_active = 1")
@SQLDelete(sql="Update hospital SET is_active = 0 where id=?")
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
    @Column(nullable = false,columnDefinition="VARCHAR(8000)")
    @NotEmpty(message = "*Please provide description of Hospital")
    private String aboutHospital;

    @Column(name="is_active",columnDefinition="TINYINT(1) default '1'")
    private Boolean active=true;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id",referencedColumnName = "id")
    private List<HospitalsImages> hospitalsImages;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "hospital_id", referencedColumnName = "id")
    private List<HospitalEmployee> hospitalsEmployee;

    public Hospital() {
    }
    public Hospital(String fullName, String city, String postalCode, String address, String aboutHospital) {
        this.fullName = fullName;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
        this.aboutHospital = aboutHospital;
    }
    public Hospital(String fullName, String city, String postalCode, String address, String aboutHospital, List<HospitalsImages> hospitalsImages, List<HospitalEmployee> hospitalsEmployee) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<HospitalsImages> getHospitalsImages() {
        return hospitalsImages;
    }

    public void setHospitalsImages(List<HospitalsImages> hospitalsImages) {
        this.hospitalsImages = hospitalsImages;
    }

    public List<HospitalEmployee> getHospitalsEmployee() {
        return hospitalsEmployee;
    }

    public void setHospitalsEmployee(List<HospitalEmployee> hospitalsEmployee) {
        this.hospitalsEmployee = hospitalsEmployee;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", address='" + address + '\'' +
                ", aboutHospital='" + aboutHospital + '\'' +
                '}';
    }
}
