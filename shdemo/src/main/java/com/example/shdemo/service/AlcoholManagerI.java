package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Person;

import java.util.List;

public interface AlcoholManagerI {
	
	void clearAlcohols();
	void addAlcohol(Alcohol alcohol);
	void deleteAlcohol(String alcoholName);
	List<Alcohol> getAllAlcohols();
	List<Alcohol> getAllAlcoholsByType(String type);
	List<Alcohol> getAllAlcoholsByParameter(String parameter, String parameterValue);
	List<Alcohol> getAlcoholsByVolt(float voltMin, float voltMax, int order);

}
