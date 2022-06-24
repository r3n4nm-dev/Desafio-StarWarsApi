package br.com.renanmateus.model;

import java.util.List;
@lombok.Data
public class Species extends Data {

	private String homeworld;
	private String classification;
	private List<String> people;

	public Species() {}
}
