package com.ntt.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ntt.finalProject.dto.InlineFilm;
import com.ntt.finalProject.model.Salle;


@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(excerptProjection = InlineFilm.class)
public interface SalleRepository extends JpaRepository<Salle, Long> {

}
