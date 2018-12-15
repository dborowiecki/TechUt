package com.example.shdemo.domain;


import org.hibernate.annotations.Entity;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "id")
    private Producer producer;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Producer getProducer() { return producer; }

    public void setProducer(Producer producer) { this.producer = producer; }
}
