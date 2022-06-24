package br.com.renanmateus.model;

import java.util.List;
@lombok.Data
public class Starships extends Data {

	private List<String> pilots;
	private String model;
	private String manufacturer;

	public Starships() {}
}
