package com.hp.backend.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;

    private String email;

    private String password;

    private String username;

    private Date created_date;

    private int gender;

    private Date dob;
    
    private int role;

    @Lob
    @Column(name = "avatar", columnDefinition = "BLOB")
    private byte[] avatar;

    private String country;

    private String city;

    private String university;

    private String major;

    private String degree;

    private String description;

    private String jobtitle;

    private String workplace;

    private String short_description;

}
