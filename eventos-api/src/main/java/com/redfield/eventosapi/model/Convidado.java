package com.redfield.eventosapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Convidado {
	@Id
	private String rg;
	
	@Column
	@NotNull
	private String nomeConvidado;
	@ManyToOne
	private Evento evento;

	public Convidado() {
		super();
	}

	public Convidado(String rg, String nomeConvidado, Evento evento) {
		super();
		this.rg = rg;
		this.nomeConvidado = nomeConvidado;
		this.evento = evento;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nome) {
		this.nomeConvidado = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
