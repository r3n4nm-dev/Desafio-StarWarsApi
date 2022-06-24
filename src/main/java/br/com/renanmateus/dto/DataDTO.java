package br.com.renanmateus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataDTO {

	protected String name;
	protected SuggestionsDTO suggestions;
	protected String url;

	public DataDTO(String name, SuggestionsDTO suggestions) {
	}

	public DataDTO() {

	}
}
