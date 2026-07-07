package br.com.fmautoserv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fmautoserv.mapper.ObjectMapper;
import jakarta.validation.Valid;

import br.com.fmautoserv.data.dto.OrdemDeServicoDTO;
import br.com.fmautoserv.model.OrdemDeServico;
import br.com.fmautoserv.services.OrdemDeServicoService;

@RestController
@RequestMapping("/api/ordem")
public class OrdemDeServicoController {

	@Autowired
	private OrdemDeServicoService service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<OrdemDeServicoDTO>> findAll() {
		return ResponseEntity.ok(ObjectMapper.parseListObjects(service.findAll(), OrdemDeServicoDTO.class));
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<OrdemDeServicoDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(ObjectMapper.parseObject(service.findById(id), OrdemDeServicoDTO.class));
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<OrdemDeServicoDTO> createOrdemDeServico(@Valid @RequestBody OrdemDeServicoDTO dto) {
		OrdemDeServico nova = service.createOrdemDeServico(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(ObjectMapper.parseObject(nova, OrdemDeServicoDTO.class));
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<OrdemDeServicoDTO> updateOrdemServico(@PathVariable Long id,
			@Valid @RequestBody OrdemDeServicoDTO dto) {
		return ResponseEntity
				.ok(ObjectMapper.parseObject(service.updateOrdemServico(id, dto), OrdemDeServicoDTO.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrdemDeServico(@PathVariable Long id) {
		service.deleteOrdemDeServico(id);
		return ResponseEntity.noContent().build();
	}
}