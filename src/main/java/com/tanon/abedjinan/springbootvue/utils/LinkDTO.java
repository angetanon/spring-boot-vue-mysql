package com.tanon.abedjinan.springbootvue.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Un DTO pour afficher un lien
 *
 * @author pakeyser
 *
 */
public class LinkDTO {
	private final String text;

	private final Long value;

	@JsonCreator
	public LinkDTO(@JsonProperty("value") Long value, @JsonProperty("text") String text) {
		super();
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Long getValue() {
		return value;
	}
}
