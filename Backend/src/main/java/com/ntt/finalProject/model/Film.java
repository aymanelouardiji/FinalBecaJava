package com.ntt.finalProject.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film extends AbstractModel<Long>{
	
	private static final long serialVersionUID = 2996009286487492970L;

	@Column(nullable = false, length = 50)
    private String titre;
	
	@Column(nullable = false)
    private int duree;

	@Column(nullable = false)
    private int annee;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="GENRE_ID")
    private Genre genre;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="NATIONALITE_ID")
	@JsonProperty
    private Nationalite nationalite;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="COMMENT_ID")
//    private Comment comment;
	@OneToMany(mappedBy = "film")
    private List<Comment> comments;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DIRECTOR_ID")
    private Personne realisateur;

    @ManyToMany
    @JoinTable(
      name = "FILM_ACTEUR",
      joinColumns = @JoinColumn(name = "FILM_ID"),
      inverseJoinColumns = @JoinColumn(name = "ACTEUR_ID"))
    private List<Personne> acteurs;
    
    @OneToMany(mappedBy = "film")
    @JsonIgnore
	private List<Seance> seances;
    
    @OneToMany(mappedBy = "film", cascade = {CascadeType.ALL})
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<Media> medias;
    
    @Column(name = "added_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date addedDate;  
}
