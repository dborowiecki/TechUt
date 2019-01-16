package com.example.shdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "producer.all", query = "Select p from Producer p"),
	@NamedQuery(name = "producer.byCode", query = "Select p from Producer p where p.code= :code")
})
public class Producer {

	private Long id;

	private String companyName = "unknown";
	private String code = "";
	private Date registrationDate = new Date();
	private Contact contact;

	private List<Alcohol> alcohols = new ArrayList<Alcohol>();

	private List<Owner> owners = new ArrayList<Owner>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getCompanyName() { return companyName; }
	public void setCompanyName(String companyName) { this.companyName = companyName; }

	@Column(unique = true, nullable = false)
	public String getCode() { return code; }
	public void setCode(String code) { this.code= code; }

	@Temporal(TemporalType.DATE)
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Alcohol> getAlcohols() {
		return alcohols;
	}
	public void setAlcohols(List<Alcohol> alcohols) {
		this.alcohols = alcohols;
	}

    @OneToOne(cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    public Contact getContact() { return contact; }
    public void setContact(Contact contact) { this.contact = contact; }

 	@ManyToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Owner> getOwners(){ return this.owners;}
	public void setOwners(List<Owner> owners) { this.owners = owners;}

}
