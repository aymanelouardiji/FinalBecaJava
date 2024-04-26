package com.ntt.finalProject.dto;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ntt.finalProject.model.Nationalite;
import com.ntt.finalProject.model.Personne;
import com.ntt.finalProject.model.Personne.TypePersonne;



@Projection(name = "inlinePersonne", types = { Personne.class }) 
public interface InlinePersonne {
	Long getId();
	String getNom();
	String getPrenom();
	String getPhoto();
	Date getDateNaissance();
	TypePersonne getTypePersonne();
	Date getAddedDate();
	Nationalite getNationalite();
}
