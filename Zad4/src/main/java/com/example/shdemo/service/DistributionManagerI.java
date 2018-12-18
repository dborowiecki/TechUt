package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Contact;
import com.example.shdemo.domain.Owner;
import com.example.shdemo.domain.Producer;

import java.util.List;

public interface DistributionManagerI {
	
	void addProducer(Producer producer);
	List<Producer> getAllProducers();
	void deleteProducer(Producer producer);
	Producer findProducerByCode(String code);
	
	void addNewAlcohol(Alcohol alcohol);
	List<Alcohol> getAvailableAlcohols();
	Alcohol findAlcoholById(Long id);

	List<Alcohol> getProducersAlcohols(Producer producer);
	void removeProducerAlcohols(Long producerId, Long alcoholId);
	void deleteAlcohol(Alcohol a);

	void addNewContact(Contact contact);
	List<Contact> getAllContacts();
	Contact getProducerContact(Producer producer);

	void addOwner(Owner owner);
	List<Owner> getAllOwners();
	void deleteOwner(Owner owner);

}
