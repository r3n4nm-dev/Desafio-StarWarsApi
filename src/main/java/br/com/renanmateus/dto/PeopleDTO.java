package br.com.renanmateus.dto;

import br.com.renanmateus.model.People;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PeopleDTO extends DataDTO {

	private String homeworld;
	private String gender;

	public PeopleDTO() {
	}
	public static PeopleDTO transform(People people) {
		return PeopleDTO.builder()
				.url(people.getUrl())
				.name(people.getName())
				.gender(people.getGender())
				.homeworld(people.getHomeworld())
				.build();
	}


}
