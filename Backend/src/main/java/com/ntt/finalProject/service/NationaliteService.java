package com.ntt.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ntt.finalProject.model.Nationalite;
import com.ntt.finalProject.repository.NationaliteRepository;

@Service
public class NationaliteService extends AbstractService<Nationalite, Long> {

    @Autowired
    private NationaliteRepository nationaliteRepository;
    

    @Override
    protected JpaRepository<Nationalite, Long> getRepository() {
        return nationaliteRepository;
    }

}
