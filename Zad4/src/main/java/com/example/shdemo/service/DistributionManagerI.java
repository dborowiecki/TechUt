package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Producer;

import java.util.List;

public interface DistributionManagerI {
	
	void addProducer(Producer producer);
	List<Producer> getAllProducers();
	void deleteProducer(Producer producer);
	Producer findProducerByCode(String code);
	
	Long addNewAlcohol(Alcohol alcohol);
	List<Alcohol> getAvailableAlcohols();
	Alcohol findAlcoholById(Long id);

	List<Alcohol> getProducersAlcohols(Producer producer);
	void removeProducerAlcohols(Long producerId, Long alcoholId);
	void deleteAlcohol(Alcohol a);

}
