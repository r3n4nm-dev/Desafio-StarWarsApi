package br.com.renanmateus.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EndpointDTO {
    private String people;
    private String planets;
    private String species;
    private String starships;
    private String vehicles;


}
