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

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ClienteDTO>> findAll() {
	    List<Cliente> listaDeClientes = service.findAll();
	    List<ClienteDTO> listaDeDTOs = ObjectMapper.parseListObjects(listaDeClientes, ClienteDTO.class);
	    return ResponseEntity.ok(listaDeDTOs);
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Long id){
		Cliente cliente = service.findById(id);
		ClienteDTO clienteDTO = ObjectMapper.parseObject(cliente, ClienteDTO.class);
		return ResponseEntity.ok(clienteDTO);
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ClienteDTO> createCliente( @Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente novoCliente = ObjectMapper.parseObject(clienteDTO, Cliente.class);
		Cliente novoClienteSalvo = service.createCliente(novoCliente);
		ClienteDTO clienteSalvoDTO = ObjectMapper.parseObject(novoClienteSalvo, ClienteDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvoDTO);
    }
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        service.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
	
}
