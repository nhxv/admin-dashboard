package com.xv.admindashboard.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xv.admindashboard.config.DateHandler;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @JsonDeserialize(using = DateHandler.class)
    private Date dateOfBirth;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "skype")
    private String skype;

    @Column(name = "email")
    private String email;

    @Column(name = "date_joined")
    @CreationTimestamp
    private Date dateJoined;

    @Column(name = "department")
    private Department department;

    @OneToMany
    @JoinColumn(name = "staff_id")
    private List<Note> notes;

    public Staff() {}

    public Staff(String firstName, String lastName, Date dateOfBirth, String gender, String address, String identityNumber, String mobile, String skype, String email, Date dateJoined, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = Gender.valueOf(gender);
        this.address = address;
        this.identityNumber = identityNumber;
        this.mobile = mobile;
        this.skype = skype;
        this.email = email;
        this.dateJoined = dateJoined;
        this.department = Department.valueOf(department);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
