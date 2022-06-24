package br.com.renanmateus.model;

@lombok.Data
public class Data {

	protected String name;
	protected String url;

	public Data() {}

	public Data(String name, String url) {
		this.name = name;
		this.url = url;
	}

}
