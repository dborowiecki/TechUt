package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DistributionManagerTest {

    @Autowired
    DistributionManagerI distributionManager;

    private final String COMPANY_NAME_1 = "Test company";
    private final String CODE_1 = "c0de";

    private final String ALCOHOL_NAME_1 = "Special";
    private final String ALCOHOL_TYPE_1 = "piwo";
    private final float ALCOHOL_VOLT_1 = 3.52f;
    private final boolean ALCOHOL_AVAILABILITY_1 = true;
    private final int ALCOHOL_YEAR_OF_PRODUCTION_1 = 1990;


    @Test
    public void distributionManagerTest() {
        assertNotNull(distributionManager);
    }

    @Test
    public void getAlcoholTest(){
        Alcohol a = new Alcohol();
        a.setName(ALCOHOL_NAME_1);
        a.setType(ALCOHOL_TYPE_1);
        a.setVolt(ALCOHOL_VOLT_1);
        a.setAvailability(ALCOHOL_AVAILABILITY_1);
        a.setYearOfProduction(ALCOHOL_YEAR_OF_PRODUCTION_1);

        List<Alcohol> alcohols = distributionManager.getAvailableAlcohols();
        for (Alcohol alcohol: alcohols) {
            if (alcohol.getName().equals(ALCOHOL_NAME_1)) {
                distributionManager.deleteAlcohol(alcohol);
            }
        }

        distributionManager.addNewAlcohol(a);

        Alcohol retrived = new Alcohol();

        alcohols = distributionManager.getAvailableAlcohols();
        for (Alcohol alcohol: alcohols) {
            if (alcohol.getName().equals(ALCOHOL_NAME_1)) {
                retrived = alcohol;
            }
        }
        assertEquals(ALCOHOL_NAME_1, retrived.getName());
    }

    @Test
    public void addProducentCheck() {
        List<Producer> retrivedProducers = distributionManager.getAllProducers();

        for (Producer producer: retrivedProducers) {
            if (producer.getCode().equals(CODE_1)) {
                distributionManager.deleteProducer(producer);
            }
        }

        Producer producer = new Producer();
        producer.setCompanyName(COMPANY_NAME_1);
        producer.setCode(CODE_1);

        distributionManager.addProducer(producer);

        Producer retrivedProducer = distributionManager.findProducerByCode(CODE_1);

        assertEquals(COMPANY_NAME_1, retrivedProducer.getCompanyName());
        assertEquals(CODE_1, retrivedProducer.getCode());
    }
}
