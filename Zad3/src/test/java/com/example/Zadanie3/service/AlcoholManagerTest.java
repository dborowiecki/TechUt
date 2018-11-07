package com.example.Zadanie3.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import java.util.ArrayList;
import java.util.List;

import com.example.Zadanie3.domain.Alcohol;
import org.junit.Test;

public class AlcoholManagerTest {
	
	
	AlcoholService AlcoholManager;
	public AlcoholManagerTest(){
		try{
			AlcoholManager = new AlcoholService();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	private final static String NAME_1 = "Chianti";
	private final static String PRODUCER_1 = "EagleW S.A.";
	private final static int PRODUCTION_YEAR_1 = 2015;
	private final static String TYPE_1 = "Wine";
	private final static float VOLT_1 = 18.2f;

	private final static String NAME_2 = "Socker";
	private final static String PRODUCER_2 = "Wilkes";
	private final static int PRODUCTION_YEAR_2 = 2005;
	private final static String TYPE_2 = "Whisky";
	private final static float VOLT_2 = 40.0f;

	private final static String NAME_3 = "Socker";
	private final static String PRODUCER_3 = "Wilkes";
	private final static int PRODUCTION_YEAR_3 = 2005;
	private final static String TYPE_3 = "Whisky";
	private final static float VOLT_3 = 40.0f;
		
	Alcohol Alcohol1 = new Alcohol(NAME_1, PRODUCER_1, PRODUCTION_YEAR_1, TYPE_1, VOLT_1);
	Alcohol Alcohol2 = new Alcohol(NAME_2, PRODUCER_2, PRODUCTION_YEAR_2, TYPE_2, VOLT_2);
	Alcohol Alcohol3 = new Alcohol(NAME_3, PRODUCER_3, PRODUCTION_YEAR_3, TYPE_3, VOLT_3);

	@Test
	public void checkConnection(){
		assertNotNull(AlcoholManager.getConnection());
	}
	
	@Test
	public void checkAdding(){
		
		Alcohol Alcohol = new Alcohol(NAME_1, PRODUCER_1, PRODUCTION_YEAR_1, TYPE_1, VOLT_1);
		
		AlcoholManager.clearAlcohols();
		assertEquals(1,AlcoholManager.addAlcohol(Alcohol));
		
		List<Alcohol> Alcohols = AlcoholManager.getAllAlcohols();
		Alcohol AlcoholRetrieved = Alcohols.get(0);
		
		assertEquals(NAME_1, AlcoholRetrieved.getName());
		assertEquals(PRODUCER_1, AlcoholRetrieved.getProducer());
		assertEquals(PRODUCTION_YEAR_1, AlcoholRetrieved.getYearOfProduction());
		assertEquals(TYPE_2, AlcoholRetrieved.getType());
		assertEquals(VOLT_2, AlcoholRetrieved.getVolt());
	}
	
	@Test
	public void checkAddAll(){
		AlcoholManager.clearAlcohols();		
		
		List<Alcohol> Alcohols = new ArrayList<>();
		Alcohols.add(Alcohol1);
		Alcohols.add(Alcohol2);
		Alcohols.add(Alcohol3);
		
		AlcoholManager.addAllAlcohols(Alcohols);
		int size = AlcoholManager.getAllAlcohols().size();
		
		assertTrue(size == 0 || size == 3);
		
		assertThat(size, either(is(3)).or(is(0)));
		
	}

}
