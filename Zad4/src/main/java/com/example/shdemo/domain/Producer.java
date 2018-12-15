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

    @OneToOne(mappedBy = "producer")
	private Contact contact;

	private List<Alcohol> alcohols = new ArrayList<Alcohol>();

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

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Alcohol> getAlcohols() {
		return alcohols;
	}
	public void setAlcohols(List<Alcohol> alcohols) {
		this.alcohols = alcohols;
	}

    public Contact getContact() { return contact; }

    public void setContact(Contact contact) { this.contact = contact; }


}
