package com.ntt.finalProject.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ntt.finalProject.model.Film;
import com.ntt.finalProject.model.Genre;
import com.ntt.finalProject.model.Nationalite;
import com.ntt.finalProject.model.Personne;
import com.ntt.finalProject.model.Seance;



@Projection(name = "inlineFilm", types = { Film.class }) 
public interface InlineFilm {
	Long getId();
	String getTitre();
	int getAnnee();
	int getDuree();
	Genre getGenre();
	Nationalite getNationalite();
	Personne getRealisateur();
	List<Personne> getActeurs();
	List<Seance> getSeances();
	Date getAddedDate();
}
