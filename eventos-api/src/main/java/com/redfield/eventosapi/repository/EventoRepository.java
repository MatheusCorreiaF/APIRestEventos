package com.redfield.eventosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redfield.eventosapi.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{
	public Evento findById(long id);
}
