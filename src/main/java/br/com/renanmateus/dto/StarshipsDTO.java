package br.com.renanmateus.dto;

import br.com.renanmateus.model.Starships;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StarshipsDTO extends DataDTO {

	private String model;
	private String manufacturer;
	private List<String> pilots;

	public StarshipsDTO() {}

	public static StarshipsDTO transform(Starships starships) {
		return StarshipsDTO.builder()
				.url(starships.getUrl())
				.name(starships.getName())
				.manufacturer(starships.getManufacturer())
				.model(starships.getModel())
				.pilots(starships.getPilots())
				.build();
	}
}
