package br.com.renanmateus.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("star-wars-api")
@Data
@NoArgsConstructor
public class StarWarsConfig {

    private String peopleMessage;
    private String planetsMessage;
    private String vehiclesMessage;
    private String speciesMessage;
    private String starshipsMessage;
    private String defaultMessage;
}
