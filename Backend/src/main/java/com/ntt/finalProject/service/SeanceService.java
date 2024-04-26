package com.ntt.finalProject.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ntt.finalProject.model.Seance;
import com.ntt.finalProject.repository.SeanceRepository;

@Service
public class SeanceService extends AbstractService<Seance, Long> {

    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    protected JpaRepository<Seance, Long> getRepository() {
        return seanceRepository;
    }
    
    public List<Seance> getSeancesParDate(Date date){
    	return seanceRepository.findByDateProjection(date);
    }

}
