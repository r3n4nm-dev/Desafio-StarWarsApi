package br.com.renanmateus.model;

import java.util.List;
@lombok.Data
public class Planets extends Data {

	private List<String> residents;
	private String terrain;
	private String climate;

	public Planets() {}

}
