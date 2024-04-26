package com.ntt.finalProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ntt.finalProject.dto.InlineGenre;
import com.ntt.finalProject.model.Genre;


@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(excerptProjection = InlineGenre.class)
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
