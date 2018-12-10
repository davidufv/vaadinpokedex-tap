package com.pidal.vaadin_pokedex;

import java.util.ArrayList;
import java.util.List;

public class Pokedex {
	
	private static Pokedex singleton;
	private List<Pokemon> pokemons;

	private Pokedex() {
		super();
		pokemons = new ArrayList<>();
	}
	
	public static Pokedex getInstance() {
		
		if(singleton == null)
			singleton = new Pokedex();
		
		return singleton;
	}
	
	public void addPokemon(Pokemon p) {
		pokemons.add(p);
	}
	
	public void deletePokemon(Pokemon p)
	{
		pokemons.remove(p);
	}
	
	public List<Pokemon> getPokemons() {
		return pokemons;
	}
	


}
