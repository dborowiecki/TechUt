package com.example.shdemo.service;

import com.example.shdemo.domain.Alcohol;
import com.example.shdemo.domain.Producer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DistributionManager implements DistributionManagerI {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addProducer(Producer producer){
        producer.setId(null);
        sessionFactory.getCurrentSession().persist(producer);
    }

    public List<Producer> getAllProducers(){

        return null;
    }

    public void deleteProducer(Producer producer){

    }

    public Producer findProducerByCode(String code){

        return null;
    }

    public Long addNewAlcohol(Alcohol alcohol){

        return null;
    }

    public List<Alcohol> getAvailableAlcohols(){

        return null;
    }
    public Alcohol findAlcoholById(Long id){

        return null;
    }

    public List<Alcohol> getProducersAlcohols(Producer producer){

        return null;
    }

    public void removeProducerAlcohols(Long producerId, Long alcoholId){

    }

}
