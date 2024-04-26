package com.ntt.finalProject.dto;



import com.ntt.finalProject.model.Film;
import com.ntt.finalProject.model.Salle;
import com.ntt.finalProject.model.Seance;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "inlineSeance", types = { Seance.class})
public interface InlineSeance {
    Long getId();

    Date getdateProjection();

    Date getheureDebut();

    Date getheureFin();

    Film getFilm();

    Salle getSalle();
}