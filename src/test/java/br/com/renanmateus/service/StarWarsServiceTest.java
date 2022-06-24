package br.com.renanmateus.service;

import br.com.renanmateus.config.StarWarsConfig;
import br.com.renanmateus.dto.*;
import br.com.renanmateus.exceptions.InternalErrorException;
import br.com.renanmateus.webclient.WebClientApiImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class StarWarsServiceTest {

    private WebClientApiImpl webClientApi;
    private StarWarsServiceImpl starWarsService;
    private StarWarsConfig starWarsConfig;


    @BeforeEach
    void setUp() {
        webClientApi = Mockito.mock(WebClientApiImpl.class);
        starWarsConfig= Mockito.mock(StarWarsConfig.class);
        starWarsService = new StarWarsServiceImpl(webClientApi, starWarsConfig);
    }

    @Test
    void must_Return_PeopleDTO_With_Suggestions_When_Exists() {
        PeopleDTO people1 = PeopleDTO.builder().gender("male").name("Luke Skywalker")
                .homeworld("https://swapi.dev/api/planet/1").url("https://swapi.dev/api/people/1/").build();

        PlanetsDTO planet = PlanetsDTO.builder().name("Endor").terrain("forests")
                .climate("temperate").url("https://swapi.dev/api/planets/1")
                .residents(List.of("https://swapi.dev/api/people/2")).build();

        SuggestionsDTO suggestions = SuggestionsDTO.builder()
                .description("People with same homeworld")
                .elements(List.of(new ElementDTO("Darth Vader", "https://swapi.dev/api/people/2"))).build();

        DataDTO dataDTO = DataDTO.builder().suggestions(suggestions).build();

        when(webClientApi.getPeople(anyLong(), anyString())).thenReturn(Mono.just(people1));
        when(webClientApi.getPlanets(anyLong(), anyString())).thenReturn(Mono.just(planet));
        when(webClientApi.getSuggestions(any(), any())).thenReturn(Mono.just(dataDTO));

        StepVerifier.create(starWarsService.getPeople(1L))
                .expectNext(PeopleDTO.builder()
                        .homeworld("Endor")
                        .name("Luke Skywalker")
                        .gender("male")
                        .suggestions(suggestions).build())
                .verifyComplete();
    }

    @Test
    void must_Return_VehiclesDTO_WithOut_Suggestions_When_Not_Exists_Suggestions() {
        VehiclesDTO vehiclesDTO = VehiclesDTO.builder().name("starfighter")
                .manufacturer("Sienar Fleet Systems").model("Twin Ion Engine")
                .pilots(new ArrayList<>())
                .build();

        when(webClientApi.getVehicles(anyLong(), anyString())).thenReturn(Mono.just(vehiclesDTO));

        StepVerifier.create(starWarsService.getVehicles(1L))
                .expectNext(VehiclesDTO.builder()
                        .manufacturer("Sienar Fleet Systems")
                        .model("Twin Ion Engine")
                        .name("starfighter")
                        .build())
                .verifyComplete();
    }

    @Test
    void must_Return_InternalErrorException_When_Internal_Server_Error_Exists() {
        when(webClientApi.getVehicles(anyLong(), anyString())).thenReturn(Mono.error(new InternalErrorException()));

        StepVerifier.create(starWarsService.getVehicles(1L))
                .expectError(InternalErrorException.class)
                .verify();
    }
}
