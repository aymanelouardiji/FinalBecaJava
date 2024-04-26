package com.ntt.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ntt.finalProject.model.Personne;
import com.ntt.finalProject.repository.PersonneRepository;

@Service
public class PersonneService extends AbstractService<Personne, Long> {

    @Autowired
    private PersonneRepository personneRepository;

    @Override
    protected JpaRepository<Personne, Long> getRepository() {
        return personneRepository;
    }
 
}
