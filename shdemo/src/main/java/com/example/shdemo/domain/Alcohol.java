package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "alcohol.producer", query = "Select a from Alcohol a where a.producer = producer")
})
public class Alcohol {

    private Long id;
    private String name;
    private String producer;
    private int productionYear;
    private String type;
    private float volt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getYearOfProduction() {
        return productionYear;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.productionYear = yearOfProduction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getVolt() {
        return volt;
    }

    public void setVolt(float volt) {
        this.volt = volt;
    }
}
