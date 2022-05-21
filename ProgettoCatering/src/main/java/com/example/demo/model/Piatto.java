package com.example.demo.model; 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Ingrediente> ingredientiDelPiatto;
	
	private String nome;
	private String descr;
	
	public Long getId() {
		return Id;
	}
	public List<Ingrediente> getIngredientiDelPiatto() {
		return ingredientiDelPiatto;
	}
	public void setIngredientiDelPiatto(List<Ingrediente> ingredientiDelPiatto) {
		this.ingredientiDelPiatto = ingredientiDelPiatto;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

}
