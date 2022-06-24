package br.com.renanmateus.webclient;

import java.util.List;

import br.com.renanmateus.dto.DataDTO;
import br.com.renanmateus.dto.ElementDTO;
import br.com.renanmateus.dto.PeopleDTO;
import br.com.renanmateus.dto.PlanetsDTO;
import br.com.renanmateus.dto.SpeciesDTO;
import br.com.renanmateus.dto.StarshipsDTO;
import br.com.renanmateus.dto.VehiclesDTO;

import reactor.core.publisher.Mono;


public interface WebClientApi {
	Mono<PeopleDTO> getPeople(long id, String url);
	Mono<PlanetsDTO> getPlanets(long id, String url);
	Mono<VehiclesDTO> getVehicles(long id, String url);
	Mono<StarshipsDTO> getStarships(long id, String url);
	Mono<SpeciesDTO> getSpecies(long id, String url);
	Mono<ElementDTO> getElement(String url);
	Mono<DataDTO> getSuggestions(List<String> stringList, List<Mono<ElementDTO>> elements);

}

