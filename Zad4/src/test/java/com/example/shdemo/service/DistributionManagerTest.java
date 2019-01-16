package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Contact;
import com.example.shdemo.domain.Owner;
import com.example.shdemo.domain.Producer;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DistributionManagerTest {

    @Autowired
    DistributionManagerI distributionManager;

    private final String COMPANY_NAME_1 = "Test company";
    private final String CODE_1 = "c0de";
    private final String COMPANY_NAME_2 = "Second test company";
    private final String CODE_2 = "cod3";

    private final String ALCOHOL_NAME_1 = "Komes";
    private final String ALCOHOL_NAME_2 = "Other name";
    private final String ALCOHOL_TYPE_1 = "piwo";
    private final float ALCOHOL_VOLT_1 = 3.52f;
    private final boolean ALCOHOL_AVAILABILITY_1 = true;
    private final int ALCOHOL_YEAR_OF_PRODUCTION_1 = 1990;

    private final String CONTACT_EMAIL_1 = "example@company.com";
    private final String CONTACT_PHONE_NUMBER_1 = "112456789";
    private final String CONTACT_EMAIL_2 = "example@company.com";
    private final String CONTACT_PHONE_NUMBER_2 = "112456789";

    private final String OWNER_NAME_1 = "John";
    private final String OWNER_SECOND_NAME_1 = "Doe";
    private final String OWNER_NAME_2 = "Jannet";
    private final String OWNER_SECOND_NAME_2 = "Doe";

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

        Alcohol recived = new Alcohol();

        alcohols = distributionManager.getAvailableAlcohols();
        for (Alcohol alcohol: alcohols) {
            if (alcohol.getName().equals(ALCOHOL_NAME_1)) {
                recived = alcohol;
            }
        }
        assertEquals(ALCOHOL_NAME_1, recived.getName());
        assertEquals(ALCOHOL_TYPE_1, recived.getType());
        assertEquals(ALCOHOL_AVAILABILITY_1, recived.getAvailability());
        assertEquals(ALCOHOL_YEAR_OF_PRODUCTION_1, recived.getYearOfProduction());
    }

    @Test
    public void updateAlcoholTest(){
        Alcohol a = new Alcohol();
        a.setName(ALCOHOL_NAME_1);
        a.setType(ALCOHOL_TYPE_1);
        a.setVolt(ALCOHOL_VOLT_1);
        a.setAvailability(ALCOHOL_AVAILABILITY_1);
        a.setYearOfProduction(ALCOHOL_YEAR_OF_PRODUCTION_1);

        distributionManager.addNewAlcohol(a);

        List<Alcohol> alcohols = distributionManager.getAvailableAlcohols();


        Alcohol recived = new Alcohol();
        for (Alcohol alcohol: alcohols) {
            if (alcohol.getName().equals(ALCOHOL_NAME_1)) {
                recived = alcohol;
            }
        }

        a.setName(ALCOHOL_NAME_2);

        assertEquals(recived.getName(), ALCOHOL_NAME_2);
        assertNotSame(ALCOHOL_NAME_1, ALCOHOL_NAME_2);
    }

    @Test
    public void getAlcoholByIdTest(){
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
        Long alcoholId = a.getId();
        Alcohol recived = new Alcohol();

        alcohols = distributionManager.getAvailableAlcohols();
        for (Alcohol alcohol: alcohols) {
            if (alcohol.getName().equals(ALCOHOL_NAME_1)) {
                recived = alcohol;
            }
        }
        assertEquals(alcoholId, recived.getId());
    }

    @Test
    public void addProducentTest() {
        List<Producer> recivedProducers = distributionManager.getAllProducers();

        for (Producer producer: recivedProducers) {
            if (producer.getCode().equals(CODE_1)) {
                distributionManager.deleteProducer(producer);
            }
        }

        Producer producer = new Producer();
        producer.setCompanyName(COMPANY_NAME_1);
        producer.setCode(CODE_1);

        distributionManager.addProducer(producer);

        Producer recivedProducer = distributionManager.findProducerByCode(CODE_1);

        assertEquals(COMPANY_NAME_1, recivedProducer.getCompanyName());
        assertEquals(CODE_1, recivedProducer.getCode());
    }

    @Test
    public void updateProducerTest(){
        Producer producer = new Producer();
        producer.setCompanyName(COMPANY_NAME_1);
        producer.setCode(CODE_1);

        distributionManager.addProducer(producer);

        Producer recivedProducer = distributionManager.findProducerByCode(CODE_1);

        producer.setCompanyName(COMPANY_NAME_2);

        assertEquals(recivedProducer.getCompanyName(), COMPANY_NAME_2);
    }

    @Test
    public void getAllProducersTest() {
        Producer p = new Producer();
        p.setCompanyName(COMPANY_NAME_1);
        p.setCode(CODE_1);
        Producer p2 = new Producer();
        p2.setCompanyName(COMPANY_NAME_2);
        p2.setCode(CODE_2);

        for (Producer producer: distributionManager.getAllProducers()) {
            if (producer.getCode().equals(CODE_1) ||
                    producer.getCode().equals(CODE_2)) {
                distributionManager.deleteProducer(producer);
            }
        }
        distributionManager.addProducer(p);
        distributionManager.addProducer(p2);

        List<Producer> recivedProducer = distributionManager.getAllProducers();

        assertEquals(recivedProducer.size(), 2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void add2ProdcersWithSameCodeExceptionTest() {
        Producer p = new Producer();
        p.setCompanyName(COMPANY_NAME_1);
        p.setCode(CODE_1);
        Producer p2 = new Producer();
        p2.setCompanyName(COMPANY_NAME_2);
        p2.setCode(CODE_1);

        for (Producer producer: distributionManager.getAllProducers()) {
            if (producer.getCode().equals(CODE_1) ||
                    producer.getCode().equals(CODE_2)) {
                distributionManager.deleteProducer(producer);
            }
        }
        distributionManager.addProducer(p);
        distributionManager.addProducer(p2);
    }

    @Test
    public void createProducerAlcoholTest(){
        Alcohol a = new Alcohol();
        a.setName(ALCOHOL_NAME_1);
        a.setType(ALCOHOL_TYPE_1);
        a.setVolt(ALCOHOL_VOLT_1);
        a.setAvailability(ALCOHOL_AVAILABILITY_1);
        a.setYearOfProduction(ALCOHOL_YEAR_OF_PRODUCTION_1);

        Producer p = new Producer();
        p.setCode(CODE_1);
        p.setCompanyName(COMPANY_NAME_1);

        distributionManager.addNewAlcohol(a);
        distributionManager.addProducer(p);

        List<Alcohol> l = new ArrayList<Alcohol>();
        l.add(a);
        p.setAlcohols(l);
        List<Alcohol> recived = distributionManager.getProducersAlcohols(p);

        assertEquals(l.get(0), recived.get(0));
        assertTrue(l.get(0).getAvailability());
    }

    @Test
    public void getAllContactTest(){
        Contact c = new Contact();
        c.setEmail(CONTACT_EMAIL_1);
        c.setPhoneNumber(CONTACT_PHONE_NUMBER_1);
        Contact c2 = new Contact();
        c.setEmail(CONTACT_EMAIL_2);
        c.setPhoneNumber(CONTACT_PHONE_NUMBER_2);

        for (Contact contact: distributionManager.getAllContacts()) {
            if (contact.getEmail().equals(CONTACT_EMAIL_1))
                distributionManager.deleteContact(contact);
        }

        distributionManager.addNewContact(c);
        distributionManager.addNewContact(c2);

        List<Contact> recived = distributionManager.getAllContacts();

        assertEquals(recived.size(), 2);
    }

    @Test
    public void addContactToProducerTest(){
        Producer p = new Producer();
        p.setCode(CODE_1);
        p.setCompanyName(COMPANY_NAME_1);

        Contact c = new Contact();
        c.setEmail(CONTACT_EMAIL_1);
        c.setPhoneNumber(CONTACT_PHONE_NUMBER_1);

        for (Producer producer: distributionManager.getAllProducers()) {
            if (producer.getCode().equals(CODE_1))
                distributionManager.deleteProducer(producer);
        }
        for (Contact contact: distributionManager.getAllContacts()) {
            if (contact.getEmail().equals(CONTACT_EMAIL_1))
                distributionManager.deleteContact(contact);
        }

        distributionManager.addNewContact(c);
        distributionManager.addProducer(p);

        p.setContact(c);

        Contact recived = distributionManager.getProducerContact(p);

        assertEquals(recived.getEmail(), c.getEmail());
        assertEquals(recived.getPhoneNumber(), c.getPhoneNumber());
    }

    @Test
    public void addOwnerTest(){
        Owner o = new Owner();
        o.setFirstName(OWNER_NAME_1);
        o.setLastName(OWNER_SECOND_NAME_1);

        for (Owner owner: distributionManager.getAllOwners()) {
            if (owner.getFirstName().equals(OWNER_NAME_1))
                distributionManager.deleteOwner(owner);
        }

        distributionManager.addOwner(o);

        List<Owner> recived = distributionManager.getAllOwners();
        Owner recivedOwner = null;
        for (Owner r: recived) {
            if(r.getFirstName() == OWNER_NAME_1 && r.getLastName() == OWNER_SECOND_NAME_1)
                recivedOwner = r;
        }
        assertEquals(recivedOwner, o);
    }

    @Test
    public void updateOwnerTest(){
        Owner o = new Owner();
        o.setFirstName(OWNER_NAME_1);
        o.setLastName(OWNER_SECOND_NAME_1);

        distributionManager.addOwner(o);

        List<Owner> recived = distributionManager.getAllOwners();
        Owner recivedOwner = null;
        for (Owner r: recived) {
            if(r.getFirstName() == OWNER_NAME_1 && r.getLastName() == OWNER_SECOND_NAME_1)
                recivedOwner = r;
        }
        o.setFirstName(OWNER_NAME_2);

        assertEquals(recivedOwner.getFirstName(), OWNER_NAME_2);
    }
    @Test
    public void getAllOwnersTest(){
        Owner o = new Owner();
        o.setFirstName(OWNER_NAME_1);
        o.setLastName(OWNER_SECOND_NAME_1);
        Owner o2 = new Owner();
        o.setFirstName(OWNER_NAME_2);
        o.setLastName(OWNER_SECOND_NAME_2);

        for (Owner owner: distributionManager.getAllOwners()) {
            if (owner.getFirstName().equals(OWNER_NAME_1))
                distributionManager.deleteOwner(owner);
        }

        distributionManager.addOwner(o);
        distributionManager.addOwner(o2);
        List<Owner> recived = distributionManager.getAllOwners();

        assertEquals(recived.size(), 2);
    }

    @Test
    public void addProducerOwnerTest(){
        Owner o = new Owner();
        o.setFirstName(OWNER_NAME_1);
        o.setLastName(OWNER_SECOND_NAME_1);

        Producer p = new Producer();
        p.setCode(CODE_1);
        p.setCompanyName(COMPANY_NAME_1);

        for (Owner owner: distributionManager.getAllOwners()) {
            if (owner.getFirstName().equals(OWNER_NAME_1))
                distributionManager.deleteOwner(owner);
        }

        for (Producer producer: distributionManager.getAllProducers()) {
            if (producer.getCode().equals(CODE_1))
                distributionManager.deleteProducer(producer);
        }

        distributionManager.addOwner(o);
        distributionManager.addProducer(p);

        p.setOwners(distributionManager.getAllOwners());
        List<Owner> recivedProducerOwners = distributionManager.getAllOwners();

        assertEquals(recivedProducerOwners.get(0), o);
    }
}
