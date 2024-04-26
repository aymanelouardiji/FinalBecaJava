package com.ntt.finalProject.dto;


import com.ntt.finalProject.model.Salle;
import com.ntt.finalProject.model.Seance;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

@Projection(name = "inlineSalle", types = { Salle.class })
public interface InlineSalle {
    Long getId();

    int getNumero();

    int getCapacite();

    List<Seance> getSeances();

    Date getaddedDate();
}
