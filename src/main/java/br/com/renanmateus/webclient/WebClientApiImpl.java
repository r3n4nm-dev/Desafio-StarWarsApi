package br.com.renanmateus.webclient;

import br.com.renanmateus.dto.*;
import br.com.renanmateus.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientApiImpl implements WebClientApi {

	private final WebClient webClient;

	public WebClientApiImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	@Override
	public Mono<PeopleDTO> getPeople(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/people/"+id : url;
		return this.webClient.get()
				.uri(uri)
				.retrieve()
				.bodyToMono(People.class).flatMap(p-> Mono.just(PeopleDTO.transform(p)));
	}
	@Override
	public Mono<PlanetsDTO> getPlanets(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/planets/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
				.bodyToMono(Planets.class).flatMap(p-> Mono.just(PlanetsDTO.transform(p)));
	}
	@Override
	public Mono<VehiclesDTO> getVehicles(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/vehicles/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
								.bodyToMono(Vehicles.class).flatMap(v -> Mono.just(VehiclesDTO.transform(v)));
	}
	@Override
	public Mono<StarshipsDTO> getStarships(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/starships/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
								.bodyToMono(Starships.class).flatMap(s-> Mono.just(StarshipsDTO.transform(s)));
	}
	@Override
	public Mono<SpeciesDTO> getSpecies(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/species/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
				.bodyToMono(Species.class).flatMap(s-> Mono.just(SpeciesDTO.transform(s)));
	}
}

