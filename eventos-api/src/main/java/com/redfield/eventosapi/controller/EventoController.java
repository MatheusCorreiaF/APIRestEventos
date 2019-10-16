package com.redfield.eventosapi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.redfield.eventosapi.model.Convidado;
import com.redfield.eventosapi.model.Evento;
import com.redfield.eventosapi.repository.EventoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/evento")
@Api(value="API Rest Eventos")
public class EventoController {

	@Autowired
	private EventoRepository eventoR;

	@PostMapping()
	@ApiOperation(value="Adiciona Evento")
	@ResponseStatus(HttpStatus.CREATED)
	public Evento addEvento(@Valid @RequestBody Evento evento) {
		return eventoR.save(evento);
	}

	@GetMapping()
	@ApiOperation(value="Lista todos Eventos")
	public List<Evento> getEventos() {
		return eventoR.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Busca Evento por ID")
	public ResponseEntity<Evento> getEvento(@PathVariable(value = "id") long id) {
		Evento evento = eventoR.findById(id);
		if (evento == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
		return ResponseEntity.ok(evento);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value="Deleta Evento por ID")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEvento(@PathVariable(value = "id") long id) {
		if (eventoR.findById(id) == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
		eventoR.deleteById(id);
	}

	@PutMapping()
	@ApiOperation(value="Atualiza Evento")
	public ResponseEntity<Evento> updateProduto(@Valid @NotNull @RequestBody Evento evento) {
		if (eventoR.findById(evento.getId()) == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");

		return ResponseEntity.ok(eventoR.save(evento));
	}

}
