package com.tracking.panel.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Where(clause="is_active = 1")
@SQLDelete(sql="Update hospital_employee SET is_active = 0 where id=?")
public class HospitalEmploye {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty(message = "Please provide doctor's First name!")
    @Size(min=2, max=30,message = "First name must be greater then 2 characters!")
    private String fName;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide doctor's Last name!")
    @Size(min=2, max=30,message = "Last name must be greater then 2 characters!")
    private String lName;

    @Column(unique = true, nullable = false)
    @Email(message = "Please provide a valid Email!")
    @NotEmpty(message = "Please provide doctor's Email!")
    @Size(min=2, max=30,message = "Email must be greater then 2 characters")
    private String email;

    @Column(name = "img_path")
    private String imgPath;

    @Column(nullable = false)
    private String title;

    private Date dob;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at= new Date();

    @LastModifiedDate
    private Date updated_at= new Date();

    @Column(name="is_active",columnDefinition="TINYINT(1) default '1'")
    private Boolean active=true;

    @Column(name="role",columnDefinition="VARCHAR(45)")
    private String role;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

}
