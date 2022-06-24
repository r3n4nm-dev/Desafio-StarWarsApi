package br.com.renanmateus.dto;

import br.com.renanmateus.model.Species;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Data
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SpeciesDTO extends DataDTO {

	private String homeworld;
	private String classification;
	private List<String> people;
	public SpeciesDTO() {}

	public static SpeciesDTO transform(Species species) {
		return SpeciesDTO.builder()
				.url(species.getUrl())
				.name(species.getName())
				.homeworld(species.getHomeworld())
				.people(species.getPeople())
				.classification(species.getClassification())
				.build();
	}
}
