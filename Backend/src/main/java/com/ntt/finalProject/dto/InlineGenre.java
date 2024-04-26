package com.ntt.finalProject.dto;

import org.springframework.data.rest.core.config.Projection;

import com.ntt.finalProject.model.Genre;

@Projection(name = "inlineGenre", types = { Genre.class }) 
public interface InlineGenre {
	 Long getId() ;
	 String getLibelle() ;
  
}
