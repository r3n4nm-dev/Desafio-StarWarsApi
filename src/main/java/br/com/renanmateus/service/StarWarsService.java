package br.com.renanmateus.service;

import br.com.renanmateus.dto.*;
import reactor.core.publisher.Mono;


public interface StarWarsService {
	Mono<PeopleDTO> getPeople(long id);
	Mono<PlanetsDTO> getPlanets(long id);
	public Mono<VehiclesDTO> getVehicles(long id);
	public Mono<StarshipsDTO> getStarships(long id);
	public Mono<SpeciesDTO> getSpecies(long id);

}
