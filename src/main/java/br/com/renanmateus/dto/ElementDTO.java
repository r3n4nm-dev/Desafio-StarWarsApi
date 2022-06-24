package br.com.renanmateus.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElementDTO {
	private String name;
	private String url;

	public ElementDTO(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public ElementDTO(){}
	
}
