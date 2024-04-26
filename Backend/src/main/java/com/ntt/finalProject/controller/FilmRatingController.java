package com.ntt.finalProject.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import com.ntt.finalProject.dto.FilmRating;
import com.ntt.finalProject.dto.FilmRatingPk;
import com.ntt.finalProject.dto.RatingDto;
import com.ntt.finalProject.model.Film;
import com.ntt.finalProject.repository.FilmRatingRepository;
import com.ntt.finalProject.repository.FilmRepository;

@RestController
@RequestMapping(path = "/films/{filmId}/ratings")
public class FilmRatingController {
	private FilmRatingRepository filmRatingRepository;
    private FilmRepository filmRepository;

    @Autowired
    public FilmRatingController(FilmRatingRepository filmRatingRepository, FilmRepository filmRepository) {
        this.filmRatingRepository = filmRatingRepository;
        this.filmRepository = filmRepository;
    }
    
    protected FilmRatingController() {

    }
    
    /**
     * Create a Film Rating.
     *
     * @param filmId tour identifier
     * @param ratingDto rating data transfer object
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createFilmRating(@PathVariable(value = "filmId") Long filmId, @RequestBody @Validated RatingDto ratingDto) {
    	Film film = verifyFilm(filmId);
    	filmRatingRepository.save(new FilmRating( new FilmRatingPk(film, ratingDto.getCustomerId()),
                ratingDto.getScore(), ratingDto.getComment()));
    }

    /**
     * Lookup a the Ratings for a film.
     *
     * @param filmId Film Identifier
     * @return All Film Ratings as RatingDto's
     */
    @GetMapping
    public List<RatingDto> getAllRatingsForFilm(@PathVariable(value = "filmId") Long filmId) {
        verifyFilm(filmId);
        return filmRatingRepository.findByPkFilmId(filmId).stream()
                .map(RatingDto::new).collect(Collectors.toList());
    }
    
    /**
     * Calculate the average Score of a Film.
     *
     * @param filmId film identifier
     * @return Tuple of "average" and the average value.
     */
    @GetMapping(path = "/average")
    public Map<String, Double> getAverage(@PathVariable(value = "filmId") Long filmId) {
        verifyFilm(filmId);
        return Map.of("average",filmRatingRepository.findByPkFilmId(filmId).stream()
                .mapToInt(FilmRating::getScore).average()
                .orElseThrow(() ->
                new NoSuchElementException("Tour has no Ratings")));
    }
    
    /**
     * Verify and return the FilmRating for a particular filmId and Customer
     * @param filmId film identifier
     * @param customerId customer identifier
     * @return the found FilmRating
     * @throws NoSuchElementException if no FilmRating found
     */
    public FilmRating verifyFilmRating(Long filmId, Long customerId) throws NoSuchElementException {
        return filmRatingRepository.findByPkFilmIdAndPkCustomerId(filmId, customerId).orElseThrow(() ->
                new NoSuchElementException("Film-Rating pair for request("
                + filmId + " for customer" + customerId));
    }
    
    /**
     * Verify and return the Film given a filmId.
     *
     * @param filmId tour identifier
     * @return the found Film
     * @throws NoSuchElementException if no Film found.
     */
    private Film verifyFilm(Long filmId) throws NoSuchElementException {
        return filmRepository.findById(Long.valueOf(filmId)).orElseThrow(() ->
            new NoSuchElementException("Tour does not exist " + filmId));
        }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();

    }
}
