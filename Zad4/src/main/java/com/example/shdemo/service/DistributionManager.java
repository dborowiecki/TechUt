package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Producer;
import org.hibernate.SessionFactory;
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

    @SuppressWarnings("unchecked")
    public List<Producer> getAllProducers(){
        return sessionFactory.getCurrentSession().getNamedQuery("producer.all").list();
    }

    @Override
    public void deleteProducer(Producer producer){
        producer = (Producer) sessionFactory.getCurrentSession().get(Person.class,
                producer.getId());

        // lazy loading here
        for (Alcohol alcohol: producer.getAlcohols()) {
            alcohol.setAvailability(false);
            sessionFactory.getCurrentSession().update(alcohol);
        }
        sessionFactory.getCurrentSession().delete(producer);
    }
    @Override
    public Producer findProducerByCode(String code){
        return (Producer) sessionFactory.getCurrentSession()
                .getNamedQuery("producer.byCode")
                .setString("code", code).uniqueResult();
    }
    @Override
    public Long addNewAlcohol(Alcohol alcohol){
        alcohol.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(alcohol);
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
        producer = (Producer) sessionFactory.getCurrentSession().get(Person.class,
                producer.getId());

        List<Alcohol> alcohols = new ArrayList<Alcohol>(producer.getAlcohols());
        return alcohols;
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

}
