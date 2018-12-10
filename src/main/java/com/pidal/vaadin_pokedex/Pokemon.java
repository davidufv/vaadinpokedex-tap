package com.pidal.vaadin_pokedex;

public class Pokemon {

	private String name;
	private String number;
	private String type;
	
	public Pokemon(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}
	public Pokemon(String number, String name, String type) {
		super();
		this.name = name;
		this.number = number;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
