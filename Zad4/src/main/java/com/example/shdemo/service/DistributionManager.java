package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Contact;
import com.example.shdemo.domain.Owner;
import com.example.shdemo.domain.Producer;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DistributionManager implements DistributionManagerI{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProducer(Producer producer){
        producer.setId(null);
        sessionFactory.getCurrentSession().persist(producer);
    }

    @Override
    public void deleteProducer(Producer producer){
        producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class,
                producer.getId());


        for (Alcohol alcohol: producer.getAlcohols()) {
            alcohol.setAvailability(false);
            sessionFactory.getCurrentSession().update(alcohol);
        }
        sessionFactory.getCurrentSession().delete(producer);
    }

    @SuppressWarnings("unchecked")
    public List<Producer> getAllProducers(){
        return sessionFactory.getCurrentSession().getNamedQuery("producer.all").list();
    }

    @Override
    public Producer findProducerByCode(String code){
        return (Producer) sessionFactory.getCurrentSession()
                .getNamedQuery("producer.byCode")
                .setString("code", code).uniqueResult();
    }
    @Override
    public void addNewAlcohol(Alcohol alcohol){
        alcohol.setId(null);
        sessionFactory.getCurrentSession().save(alcohol);
    }
    @Override
    public void deleteAlcohol(Alcohol alcohol){
        alcohol = (Alcohol) sessionFactory.getCurrentSession().get(Alcohol.class,
                alcohol.getId());

        sessionFactory.getCurrentSession().delete(alcohol);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Alcohol> getAvailableAlcohols(){

        return sessionFactory.getCurrentSession().getNamedQuery("alcohol.availability")
                .list();
    }
    @Override
    public Alcohol findAlcoholById(Long id){
        return (Alcohol) sessionFactory.getCurrentSession().get(Alcohol.class, id);
    }
    @Override
    public List<Alcohol> getProducersAlcohols(Producer producer){
        producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class,
                producer.getId());

        List<Alcohol> alcohols = new ArrayList<Alcohol>(producer.getAlcohols());
        return alcohols;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Producer> getProducerByRegistrationDate(){
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Producer.class);
        criteria.addOrder(Order.asc("registrationDate"));
        List<Producer> sortedProducers=(List<Producer>)criteria.list();
        return sortedProducers;
    }
    @Override
    public void removeProducerAlcohols(Long producerId, Long alcoholId){
        Producer producer = (Producer) sessionFactory.getCurrentSession().get(
                Producer.class, producerId);
        Alcohol alcohol = (Alcohol) sessionFactory.getCurrentSession()
                .get(Alcohol.class, alcoholId);
        alcohol.setAvailability(true);
        producer.getAlcohols().add(alcohol);
    }
    @Override
    public void addNewContact(Contact contact){
        contact.setId(null);
        sessionFactory.getCurrentSession().save(contact);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Contact> getAllContacts(){
        return sessionFactory.getCurrentSession().getNamedQuery("contact.all")
                .list();
    }
    @Override
    public Contact getProducerContact(Producer producer){
        producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class,
                producer.getId());

        Contact contact = producer.getContact();
        return contact;
    }
    @Override
    public void deleteContact(Contact contact){
        contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class,
                contact.getId());

        sessionFactory.getCurrentSession().delete(contact);
    }
    @Override
    public void addOwner(Owner owner){
        owner.setId(null);
        sessionFactory.getCurrentSession().save(owner);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Owner> getAllOwners(){
        return sessionFactory.getCurrentSession().getNamedQuery("owner.all")
                .list();
    }
    @Override
    public void deleteOwner(Owner owner){
        owner = (Owner) sessionFactory.getCurrentSession().get(Owner.class,
                owner.getId());

        sessionFactory.getCurrentSession().delete(owner);
    }

}
