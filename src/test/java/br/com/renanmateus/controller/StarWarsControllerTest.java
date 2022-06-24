package br.com.renanmateus.controller;

import br.com.renanmateus.dto.PeopleDTO;
import br.com.renanmateus.dto.PlanetsDTO;
import br.com.renanmateus.exceptions.ElementNotFoundException;
import br.com.renanmateus.exceptions.InternalErrorException;
import br.com.renanmateus.service.StarWarsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(StarWarsController.class)
public class StarWarsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StarWarsServiceImpl starWarsService;


    @Test
    public void must_Return_People_When_People_Exists() {
        when(starWarsService.getPeople(anyLong())).thenReturn(Mono.just(new PeopleDTO()));

        webTestClient
                .get().uri("/api/people/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PeopleDTO.class);
    }

    @Test
    public void must_Return_Planet_When_Planet_Exists() {
        when(starWarsService.getPlanets(anyLong())).thenReturn(Mono.just(new PlanetsDTO()));

        webTestClient
                .get().uri("/api/planets/1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(PlanetsDTO.class);
    }

    @Test
    public void must_Return_ElementNotFoundException_When_People_Not_Exists() {
        when(starWarsService.getPeople(anyLong())).thenThrow(new ElementNotFoundException());

        webTestClient
                .get().uri("/api/people/0")
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody()
                .jsonPath("message").isEqualTo("Element not found");
    }

    @Test
    public void must_Return_InternalErrorException_When_GenericError_Exists() {
        when(starWarsService.getPlanets(anyLong())).thenThrow(new InternalErrorException());

        webTestClient
                .get().uri("/api/planets/999")
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .expectBody()
                .jsonPath("message").isEqualTo("Internal Server Error");
    }
}


