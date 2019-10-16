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
import com.redfield.eventosapi.repository.ConvidadoRepository;
import com.redfield.eventosapi.repository.EventoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/evento/{eventoId}/convidado")
@Api(value="API Rest Convidados")
public class ConvidadoController {

	@Autowired
	private ConvidadoRepository convidadoR;
	
	@Autowired
	private EventoRepository eventoR;	
	
	@GetMapping()
	@ApiOperation(value="Lista todos Convidados por Evento")
	public List<Convidado> getConvidados(@NotNull @PathVariable(value = "eventoId") int eventoId)
	{
		Evento evento = eventoR.findById(eventoId);
		if(evento==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
		return convidadoR.findByEvento(evento);
	}
	
	@GetMapping("/{rg}")
	@ApiOperation(value="Busca Convidado por RG")
	public Convidado getConvidado(@NotNull @PathVariable(value = "rg") String rg)
	{
		Convidado convidado = convidadoR.findByRg(rg);
		if(convidado==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Convidado não encontrado!");
		return convidadoR.findByRg(convidado.getRg());
	}
	
	@PostMapping()
	@ApiOperation(value="Adiciona Convidado")
	@ResponseStatus(HttpStatus.CREATED)
	public Convidado addConvidado(@PathVariable(value = "eventoId") int eventoId,
									@NotNull @Valid @RequestBody Convidado convidado)
	{
		Evento evento = eventoR.findById(eventoId);
		if(evento==null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Evento não encontrado!");
		convidado.setEvento(eventoR.findById(eventoId));
		return convidadoR.save(convidado);
	}
    
	@DeleteMapping("/{rg}")
	@ApiOperation(value="Deleta Convidado por RG")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduto(@PathVariable(value="rg") String rg)
	{
		Convidado convidado = convidadoR.findByRg(rg);
		if(convidado==null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Convidado não encontrado!");
		}
		convidadoR.delete(convidado);
	}

	@PutMapping
	@ApiOperation(value="Atualiza Convidado")
	@ResponseStatus(value = HttpStatus.OK)
	public Convidado updateConvidado(@NotNull @Valid @RequestBody Convidado convidado)
	{
		if(convidadoR.findByRg(convidado.getRg())==null)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				"Convidado não encontrado!");
		}
		
		return convidadoR.save(convidado);
	}

}
