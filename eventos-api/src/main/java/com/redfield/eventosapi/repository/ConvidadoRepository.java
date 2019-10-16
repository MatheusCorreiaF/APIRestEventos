package com.redfield.eventosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redfield.eventosapi.model.Convidado;
import com.redfield.eventosapi.model.Evento;

public interface ConvidadoRepository extends JpaRepository<Convidado, String>{

	List<Convidado> findByEvento(Evento evento);

	Convidado findByRg(String rg);

}
