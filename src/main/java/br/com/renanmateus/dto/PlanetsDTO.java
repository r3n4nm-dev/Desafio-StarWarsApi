package br.com.renanmateus.dto;

import br.com.renanmateus.model.Planets;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlanetsDTO extends DataDTO {

	private String terrain;
	private String climate;
	private List<String> residents;
	public PlanetsDTO() {
	}

	public static PlanetsDTO transform(Planets planets) {
		return PlanetsDTO.builder()
				.url(planets.getUrl())
				.name(planets.getName())
				.climate(planets.getClimate())
				.terrain(planets.getTerrain())
				.residents(planets.getResidents())
				.build();
	}
}
