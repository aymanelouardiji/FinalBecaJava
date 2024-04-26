package com.ntt.finalProject.dto;

import org.springframework.data.rest.core.config.Projection;

import com.ntt.finalProject.model.Genre;
import com.ntt.finalProject.model.Media;

@Projection(name = "inlineMedia", types = { Media.class }) 
public interface InlineMedia {
	String getMedia();

}
