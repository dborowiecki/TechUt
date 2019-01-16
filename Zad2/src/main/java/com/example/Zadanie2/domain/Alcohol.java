package com.example.Zadanie2.domain;

public class Alcohol {
    private long id;
    private String name;
    private String producer;
    private int productionYear;
    private String type;
    private float volt;


    public Alcohol(){

    }
    public Alcohol(String name, String producer, int productionYear, String type, float volt){
        this.name = name;
        this.producer = producer;
        this.productionYear = productionYear;
        this.type = type;
        this.volt = volt;
    }
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
