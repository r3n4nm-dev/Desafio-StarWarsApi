package br.com.renanmateus.service;

import br.com.renanmateus.config.StarWarsConfig;
import br.com.renanmateus.dto.*;
import br.com.renanmateus.webclient.WebClientApi;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StarWarsServiceImpl implements StarWarsService{

	private final WebClientApi webClientApi;

	private final StarWarsConfig starWarsConfig;

	public StarWarsServiceImpl(WebClientApi webClientApi, StarWarsConfig starWarsConfig) {
		this.webClientApi = webClientApi;
		this.starWarsConfig = starWarsConfig;
	}
	@Override
	public Mono<PeopleDTO> getPeople(long id) {
		PeopleDTO peopleDTO = new PeopleDTO();
		return webClientApi.getPeople(id, "")
				.zipWhen(planet -> this.webClientApi.getPlanets(0, planet.getHomeworld()))
				.flatMap(t -> {
					peopleDTO.setName(t.getT1().getName());
					peopleDTO.setGender(t.getT1().getGender());
					peopleDTO.setHomeworld(t.getT2().getName());
					peopleDTO.setUrl(t.getT1().getUrl());
					return Mono.just(t);
				}).flatMap(planets -> {
					List<String> residents = planets.getT2().getResidents().stream()
							.filter(planet -> !planets.getT1().getUrl().equals(planet))
							.limit(3).collect(Collectors.toList());
					if (residents.isEmpty()) {
						peopleDTO.setSuggestions(new SuggestionsDTO(starWarsConfig.getDefaultMessage()));
						return Mono.just(peopleDTO);
					}
					return webClientApi.getSuggestions(residents, getElements(residents)).flatMap(data -> {
						data.getSuggestions().setDescription(starWarsConfig.getPeopleMessage());
						peopleDTO.setSuggestions(data.getSuggestions());
						return Mono.just(peopleDTO);
					});
				});
	}
	@Override
	public Mono<PlanetsDTO> getPlanets(long id) {
		PlanetsDTO planetsDTO = new PlanetsDTO();
		return this.webClientApi.getPlanets(id, "").flatMap( planet -> {
			planetsDTO.setName(planet.getName());
			planetsDTO.setClimate(planet.getClimate());
			planetsDTO.setTerrain(planet.getTerrain());
			List<String> residents = planet.getResidents().stream().limit(3).collect(Collectors.toList());
			if (residents.isEmpty()) {
				planetsDTO.setSuggestions(new SuggestionsDTO(starWarsConfig.getDefaultMessage()));
				return Mono.just(planetsDTO);
			}
					return webClientApi.getSuggestions(residents, getElements(residents)).flatMap(data -> {
						data.getSuggestions().setDescription(starWarsConfig.getPlanetsMessage());
						planetsDTO.setSuggestions(data.getSuggestions());
						return Mono.just(planetsDTO);
					});
			});
	}
	@Override
	public Mono<VehiclesDTO> getVehicles(long id) {
		VehiclesDTO vehiclesDTO = new VehiclesDTO();
		return this.webClientApi.getVehicles(id, "").flatMap(vehicle -> {
			vehiclesDTO.setName(vehicle.getName());
			vehiclesDTO.setManufacturer(vehicle.getManufacturer());
			vehiclesDTO.setModel(vehicle.getModel());
			List<String> pilots = vehicle.getPilots().stream().limit(3).collect(Collectors.toList());
			if (pilots.isEmpty()) {
				vehiclesDTO.setSuggestions(new SuggestionsDTO(starWarsConfig.getDefaultMessage()));
				return Mono.just(vehiclesDTO);
			}
			return webClientApi.getSuggestions(pilots, getElements(pilots)).flatMap(data -> {
				data.getSuggestions().setDescription(starWarsConfig.getVehiclesMessage());
				vehiclesDTO.setSuggestions(data.getSuggestions());
				return Mono.just(vehiclesDTO);
			});
		});
	}
	@Override
	public Mono<StarshipsDTO> getStarships(long id) {
		StarshipsDTO starshipsDTO = new StarshipsDTO();
		return this.webClientApi.getStarships(id, "").flatMap(starships -> {
			starshipsDTO.setName(starships.getName());
			starshipsDTO.setManufacturer(starships.getManufacturer());
			starshipsDTO.setModel(starships.getModel());
			List<String> pilots = starships.getPilots().stream().limit(3).collect(Collectors.toList());
			if (pilots.isEmpty()) {
				starshipsDTO.setSuggestions(new SuggestionsDTO(starWarsConfig.getDefaultMessage()));
				return Mono.just(starshipsDTO);
			}
			return webClientApi.getSuggestions(pilots, getElements(pilots)).flatMap(data -> {
				data.getSuggestions().setDescription(starWarsConfig.getStarshipsMessage());
				starshipsDTO.setSuggestions(data.getSuggestions());
				return Mono.just(starshipsDTO);
			});
		});
	}
	@Override
	public Mono<SpeciesDTO> getSpecies(long id) {
		SpeciesDTO speciesDTO = new SpeciesDTO();
		return webClientApi.getSpecies(id, "")
				.zipWhen(planet -> {
		if (planet.getHomeworld() == null) { return Mono.just(planet); }
		return this.webClientApi.getPlanets(0, planet.getHomeworld());
				})
				.flatMap(t -> {
					if(t.getT1().getHomeworld() == null) {
						speciesDTO.setHomeworld(null);
					} else speciesDTO.setHomeworld(t.getT2().getName());
					speciesDTO.setName(t.getT1().getName());
					speciesDTO.setClassification(t.getT1().getClassification());
					return Mono.just(t);
				}).flatMap(planets -> {
					List<String> people = planets.getT1().getPeople().stream()
							.filter(planet -> !planets.getT1().getUrl().equals(planet))
							.limit(3).collect(Collectors.toList());
					if (people.isEmpty()) {
						speciesDTO.setSuggestions(new SuggestionsDTO(starWarsConfig.getDefaultMessage()));
						return Mono.just(speciesDTO);
					}
					return webClientApi.getSuggestions(people, getElements(people)).flatMap(data -> {
						data.getSuggestions().setDescription(starWarsConfig.getSpeciesMessage());
						speciesDTO.setSuggestions(data.getSuggestions());
						return Mono.just(speciesDTO);
					});
				});
	}

	private List<Mono<ElementDTO>> getElements(List<String> stringList) {
		List<Mono<ElementDTO>> elementMonoList = Arrays.asList(Mono.just(new ElementDTO()),
				Mono.just(new ElementDTO()), Mono.just(new ElementDTO()));
		for (int i = 0; i < stringList.size(); i++) {
			elementMonoList.set(i, this.webClientApi.getElement(stringList.get(i)));
		}
		return elementMonoList;
	}
}
