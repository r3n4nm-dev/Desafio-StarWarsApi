package br.com.renanmateus.dto;

import br.com.renanmateus.model.Vehicles;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VehiclesDTO extends DataDTO {

	private String model;
	private String manufacturer;
	private List<String> pilots;

	public VehiclesDTO() {
	}

	public static VehiclesDTO transform(Vehicles vehicles) {
		return VehiclesDTO.builder()
				.url(vehicles.getUrl())
				.name(vehicles.getName())
				.manufacturer(vehicles.getManufacturer())
				.model(vehicles.getModel())
				.pilots(vehicles.getPilots())
				.build();
	}
}
