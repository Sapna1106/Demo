package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formId;

    private Object aadhaarNumber;
    private String firstName;
    private String fatherName;
    private Instant dob;
    private String contactPerson;
    private String gender;
    private String mobileNumber;
    private String address;
    private String houseNo;
    private String landmark;
    private String city;
    private String street;
    private String post;
    private String district;
    private String state;
    private int pincode;
    private String country;

}
