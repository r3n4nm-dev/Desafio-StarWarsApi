package br.com.renanmateus.webclient;

import br.com.renanmateus.dto.*;
import reactor.core.publisher.Mono;

public interface WebClientApi {
	 Mono<PeopleDTO> getPeople(long id, String url);
	 Mono<PlanetsDTO> getPlanets(long id, String url);
	 Mono<VehiclesDTO> getVehicles(long id, String url);
	 Mono<StarshipsDTO> getStarships(long id, String url);
	 Mono<SpeciesDTO> getSpecies(long id, String url);

}

