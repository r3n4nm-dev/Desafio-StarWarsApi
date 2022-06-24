package br.com.renanmateus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.renanmateus.dto.PeopleDTO;
import br.com.renanmateus.dto.PlanetsDTO;
import br.com.renanmateus.dto.SpeciesDTO;
import br.com.renanmateus.dto.StarshipsDTO;
import br.com.renanmateus.dto.VehiclesDTO;
import br.com.renanmateus.service.StarWarsServiceImpl;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class StarWarsController {

	@Autowired
	private StarWarsServiceImpl starWarsService;

	@GetMapping(value = "/people/{id}")
	public Mono<PeopleDTO> getPeople(@PathVariable("id") long id) {
		return starWarsService.getPeople(id);
	}

	@GetMapping(value = "/planets/{id}")
	public Mono<PlanetsDTO> getPlanets(@PathVariable("id") long id) {
		return starWarsService.getPlanets(id);
	}

	@GetMapping(value = "/vehicles/{id}")
	public Mono<VehiclesDTO> getVehicles(@PathVariable("id") long id) {
		return starWarsService.getVehicles(id);
	}

	@GetMapping(value = "/starships/{id}")
	public Mono<StarshipsDTO> getStarships(@PathVariable("id") long id) {
		return starWarsService.getStarships(id);
	}

	@GetMapping(value = "/species/{id}")
	public Mono<SpeciesDTO> getSpecies(@PathVariable("id") long id) {
		return starWarsService.getSpecies(id);
	}
}
