package br.com.renanmateus.webclient;

import br.com.renanmateus.dto.*;
import br.com.renanmateus.exceptions.ElementNotFoundException;
import br.com.renanmateus.exceptions.InternalErrorException;
import br.com.renanmateus.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

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
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new ElementNotFoundException())
				).onStatus(
						HttpStatus::is5xxServerError,
						error -> Mono.error(new InternalErrorException())
				)
				.bodyToMono(People.class).flatMap(p-> Mono.just(PeopleDTO.transform(p)));
	}
	@Override
	public Mono<PlanetsDTO> getPlanets(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/planets/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new ElementNotFoundException())
				).onStatus(
						HttpStatus::is5xxServerError,
						error -> Mono.error(new InternalErrorException())
				)
				.bodyToMono(Planets.class).flatMap(p-> Mono.just(PlanetsDTO.transform(p)));
	}
	@Override
	public Mono<VehiclesDTO> getVehicles(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/vehicles/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new ElementNotFoundException())
				).onStatus(
						HttpStatus::is5xxServerError,
						error -> Mono.error(new InternalErrorException())
				)
								.bodyToMono(Vehicles.class).flatMap(v -> Mono.just(VehiclesDTO.transform(v)));
	}
	@Override
	public Mono<StarshipsDTO> getStarships(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/starships/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new ElementNotFoundException())
				).onStatus(
						HttpStatus::is5xxServerError,
						error -> Mono.error(new InternalErrorException())
				)
								.bodyToMono(Starships.class).flatMap(s-> Mono.just(StarshipsDTO.transform(s)));
	}
	@Override
	public Mono<SpeciesDTO> getSpecies(long id, String url) {
		String uri = url.isBlank() ? "https://swapi.dev/api/species/"+id : url;
		return this.webClient.get().uri(uri).retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new ElementNotFoundException())
				).onStatus(
						HttpStatus::is5xxServerError,
						error -> Mono.error(new InternalErrorException())
				)
				.bodyToMono(Species.class).flatMap(s-> Mono.just(SpeciesDTO.transform(s)));
	}
	@Override
	public Mono<ElementDTO> getElement(String url) {
		return this.webClient.get().uri(url).retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						error -> Mono.error(new ElementNotFoundException()))
				.onStatus(HttpStatus::is5xxServerError,
						error -> Mono.error(new InternalErrorException()))
				.bodyToMono(ElementDTO.class);
	}
	@Override
	public Mono<DataDTO> getSuggestions(List<String> stringList, List<Mono<ElementDTO>> elements) {
		DataDTO dataDTO = new DataDTO();
		List<ElementDTO> elementList = new ArrayList<>();
		return Mono.zip(elements.get(0), elements.get(1), elements.get(2))
				.map(objects -> {
					if (objects.getT1().getName() != null) {
						elementList.add(new ElementDTO(objects.getT1().getName(), stringList.get(0)));
					}
					if (objects.getT2().getName() != null) {
						elementList.add(new ElementDTO(objects.getT2().getName(), stringList.get(1)));
					}
					if (objects.getT3().getName() != null) {
						elementList.add(new ElementDTO(objects.getT3().getName(), stringList.get(2)));
					}
					dataDTO.setSuggestions(new SuggestionsDTO(elementList));
					return dataDTO;
				});
	}

}

