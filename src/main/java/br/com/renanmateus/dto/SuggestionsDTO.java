package br.com.renanmateus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SuggestionsDTO {
	private String description;
	private List<ElementDTO> elements;

	public SuggestionsDTO(String description) {
		this.description = description;
	}

	public SuggestionsDTO(List<ElementDTO> elements) {
		this.elements = elements;
	}
}
