package com.tracking.panel.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Where(clause="is_active = 1")
@SQLDelete(sql="Update user SET is_active = 0 where id=?")
@Table(name = "user")
public class User  {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide your First name!")
    @Size(min=2, max=30,message = "First name must be greater then 2 characters!")
    private String fName;

    @Column(nullable = false)
    @NotEmpty(message = "Please provide your Last name!")
    @Size(min=2, max=30,message = "Last name must be greater then 2 characters!")
    private String lName;

    @Column(unique = true, nullable = false)
    @Email(message = "Please provide a valid Email!")
    @NotEmpty(message = "Please provide your Email!")
    @Size(min=2, max=30,message = "Email must be greater then 2 characters")
    private String email;

    @Column(nullable = false)
    @Size(min=6, max=255,message = "Minimum 6 length!")
    @NotEmpty(message = "Please provide your password!")
    private String password;

    @Transient
    private String repeatPassword;

    @Column(name = "img_path")
    private String imgPath;

    private Date dob;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at= new Date();

    @LastModifiedDate
    private Date updated_at= new Date();

    @Column(name="is_active",columnDefinition="TINYINT(1) default '1'")
    private Boolean active=true;

    @Column(name="role",columnDefinition="VARCHAR(45) default 'USER'")
    private String role;


    public Boolean getActive() {
        return active;
    }

    public User() {}

    public User(String fName, String lName, String email, String password, Date created_at,String role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.role=role;
    }

    public User(String fName, String lName, String email, String password, String imgPath, Boolean active, String role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.imgPath = imgPath;
        this.active = active;
        this.role=role;
    }

    public User(String fName, String lName, String email, String password, Date dob, Date created_at, Date updated_at, Boolean active, String role) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }
    @PreUpdate
    public void setUpdated_at() {
        this.updated_at = new Date();
    }

    public Boolean getactive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", dob=" + dob +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", active=" + active +
                ", role='" + role + '\'' +
                '}';
    }
}
