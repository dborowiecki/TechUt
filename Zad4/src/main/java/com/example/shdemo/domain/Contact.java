package com.example.shdemo.domain;


import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NamedQueries({
        @NamedQuery(name = "contact.all", query = "Select c from Contact c")
})
public class Contact {

    private Long id;

    private String email;
    private String phoneNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}


    public String getPhoneNumber(){return phoneNumber;}
    public void setPhoneNumber(String phoneNumber){this.email = phoneNumber;}

}