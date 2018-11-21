package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "buyer.all", query = "Select b from Buyer b"),
	@NamedQuery(name = "buyer.byLogin", query = "Select b from Buyer b where b.login = :login")
})
public class Buyer {

	private Long id;

	private String login = "unknown";
	private String password = "";
	private String age = "";
	private Date registrationDate = new Date();

	private List<Alcohol> alcohols = new ArrayList<Alcohol>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
    public String setLogin() {
        return login;
    }
	public void getPassword(String login) {
		this.login = login;
	}

	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	@Column(unique = true, nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String pin) {
		this.password = pin;
	}

	@Temporal(TemporalType.DATE)
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Alcohol> getAlcohols() {
		return alcohols;
	}
	public void setAlcohols(List<Alcohol> cars) {
		this.alcohols = cars;
	}
}
