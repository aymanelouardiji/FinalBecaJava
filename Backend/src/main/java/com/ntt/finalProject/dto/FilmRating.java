package com.ntt.finalProject.dto;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class FilmRating {

	@EmbeddedId
	private FilmRatingPk pk;

	@Column(nullable = false)
	private Integer score;

	@Column
	private String comment;

	/**
	 * Create a fully initialized FilmRating.
	 *
	 * @param pk      primiary key of a film and customer id.
	 * @param score   Integer score (1-5)
	 * @param comment Optional comment from the customer
	 */
	public FilmRating(FilmRatingPk pk, Integer score, String comment) {
		this.pk = pk;
		this.score = score;
		this.comment = comment;
	}

	protected FilmRating() {
	}

	@Override
	public String toString() {
		return "FilmRating{" + "pk=" + pk + ", score=" + score + ", comment='" + comment + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FilmRating that = (FilmRating) o;
		return Objects.equals(pk, that.pk) && Objects.equals(score, that.score)
				&& Objects.equals(comment, that.comment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pk, score, comment);
	}

	public FilmRatingPk getPk() {
		return pk;
	}

	public Integer getScore() {
		return score;
	}

	public String getComment() {
		return comment;
	}

	public void setPk(FilmRatingPk pk) {
		this.pk = pk;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
