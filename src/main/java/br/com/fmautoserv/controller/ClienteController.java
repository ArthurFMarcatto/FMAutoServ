package br.com.fmautoserv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fmautoserv.data.dto.ClienteDTO;
import br.com.fmautoserv.mapper.ObjectMapper;
import br.com.fmautoserv.model.Cliente;
import br.com.fmautoserv.services.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listaDeClientes = service.findAll();
		return ResponseEntity.ok(ObjectMapper.parseListObjects(listaDeClientes, ClienteDTO.class));
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok(ObjectMapper.parseObject(cliente, ClienteDTO.class));
	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente novoCliente = service.createCliente(ObjectMapper.parseObject(clienteDTO, Cliente.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(ObjectMapper.parseObject(novoCliente, ClienteDTO.class));
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {

		Cliente cliente = ObjectMapper.parseObject(clienteDTO, Cliente.class);

		Cliente clienteAtualizado = service.updateCliente(id, cliente);

		return ResponseEntity.ok(ObjectMapper.parseObject(clienteAtualizado, ClienteDTO.class));
	}

	@PatchMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ClienteDTO> patchCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {

		Cliente cliente = ObjectMapper.parseObject(clienteDTO, Cliente.class);

		Cliente clienteAtualizado = service.updateCliente(id, cliente);

		return ResponseEntity.ok(ObjectMapper.parseObject(clienteAtualizado, ClienteDTO.class));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		service.deleteCliente(id);
		return ResponseEntity.noContent().build();
	}

}
