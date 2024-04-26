package com.ntt.finalProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ntt.finalProject.dto.FilmRating;
import com.ntt.finalProject.dto.FilmRatingPk;



@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource(exported = false)
public interface FilmRatingRepository extends CrudRepository<FilmRating, FilmRatingPk>{

    List<FilmRating> findByPkFilmId(Long filmId);


    Optional<FilmRating> findByPkFilmIdAndPkCustomerId(Long tourId, Long customerId);
}
