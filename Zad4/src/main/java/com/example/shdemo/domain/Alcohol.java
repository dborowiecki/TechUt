package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "alcohol.availability", query = "Select c from Alcohol c where c.availability = true")
})
public class Alcohol {

	private long id;
	private String name;
	private int productionYear;
	private String type;
	private float volt;
	private Boolean availability = true;

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

	public boolean getAvailability() { return availability;}

	public void setAvailability(Boolean availability) { this.availability = availability;}
}
