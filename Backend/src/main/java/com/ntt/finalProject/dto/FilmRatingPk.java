package com.ntt.finalProject.dto;

import java.io.Serializable;

import com.ntt.finalProject.model.Film;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

@Embeddable
public class FilmRatingPk implements Serializable{

	private static final long serialVersionUID = 1975988172576602969L;

	@ManyToOne
    private Film film;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer customerId;

    public FilmRatingPk() {
    }

    /**
     * Fully initialize a Film Rating Pk
     *
     * @param film          the tour.
     * @param customerId    the customer identifier.
     */
    public FilmRatingPk(Film film, Integer customerId) {
        this.film = film;
        this.customerId = customerId;
    }

    public Film getFilm() {
        return film;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }

        FilmRatingPk that = (FilmRatingPk) o;

        if (!film.equals(that.film)) {
        	return false;
        }
        return customerId.equals(that.customerId);

    }

    @Override
    public int hashCode() {
        int result = film.hashCode();
        result = 31 * result + customerId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FilmRatingPk{" +
                "film=" + film +
                ", customerId=" + customerId +
                '}';
    }
}
