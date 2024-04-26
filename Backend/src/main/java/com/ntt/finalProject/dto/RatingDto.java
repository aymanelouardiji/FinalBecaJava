package com.ntt.finalProject.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RatingDto {
	@Min(0)
	@Max(5)
	private Integer score;

	//@Size(max = 255)
	private String comment;

	@NotNull
	private Integer customerId;

	/**
	 * Construct a RatingDto from a fully instantiated FilmRating.
	 *
	 * @param filmRating Film Rating Object
	 */
	public RatingDto(FilmRating filmRating) {
		this(filmRating.getScore(), filmRating.getComment(), filmRating.getPk().getCustomerId());
	}

	/**
	 * Constructor to fully initialize the RatingDto
	 *
	 * @param score      score 1-5
	 * @param comment    comment
	 * @param customerId customer identifier
	 */
	private RatingDto(Integer score, String comment, Integer customerId) {
		this.score = score;
		this.comment = comment;
		this.customerId = customerId;
	}

	protected RatingDto() {
	}

	public Integer getScore() {
		return score;
	}

	public String getComment() {
		return comment;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
}
