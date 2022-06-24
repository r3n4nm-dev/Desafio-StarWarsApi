package br.com.renanmateus.controller;

import br.com.renanmateus.dto.*;
import br.com.renanmateus.webclient.WebClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class StarWarsController {

	@Autowired
	private WebClientApi webClientApi;
	
	@GetMapping(value = "/people/{id}")
	public Mono<PeopleDTO> getPeople(@PathVariable("id") long id) {
		return this.webClientApi.getPeople(id, "");
	}
	
	@GetMapping(value = "/planets/{id}")
	public Mono<PlanetsDTO> getPlanets(@PathVariable("id") long id) {
		return this.webClientApi.getPlanets(id,"");
	}
	
	@GetMapping(value = "/vehicles/{id}")
	public Mono<VehiclesDTO> getVehicles(@PathVariable("id") long id) {
		return this.webClientApi.getVehicles(id, "");
	}
	
	@GetMapping(value = "/starships/{id}")
	public Mono<StarshipsDTO> getStarships(@PathVariable("id") long id) {
		return this.webClientApi.getStarships(id, "");
	}
	
	@GetMapping(value = "/species/{id}")
	public Mono<SpeciesDTO> getSpecies(@PathVariable("id") long id) {
		return this.webClientApi.getSpecies(id,"");
	}
}
