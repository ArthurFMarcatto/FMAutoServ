package br.com.fmautoserv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fmautoserv.data.dto.VeiculoDTO;
import br.com.fmautoserv.mapper.ObjectMapper;
import br.com.fmautoserv.model.Cliente;
import br.com.fmautoserv.model.Veiculo;
import br.com.fmautoserv.services.VeiculoService;

@RestController
@RequestMapping("api/veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoService service;

	@GetMapping
	public ResponseEntity<List<VeiculoDTO>> findAll() {

		List<Veiculo> listaVeiculos = service.findAll();
		List<VeiculoDTO> listaVeiculosDtos = ObjectMapper.parseListObjects(listaVeiculos, VeiculoDTO.class);

		listaVeiculosDtos.forEach(dto -> {
			Veiculo v = listaVeiculos.stream().filter(veiculo -> veiculo.getIdveiculo().equals(dto.getIdveiculo()))
					.findFirst().orElse(null);

			if (v != null && v.getCliente() != null) {
				dto.setClienteId(v.getCliente().getIdcliente());
			}
		});

		return ResponseEntity.ok(listaVeiculosDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id) {

		Veiculo veiculo = service.findById(id);
		VeiculoDTO veiculoDTO = ObjectMapper.parseObject(veiculo, VeiculoDTO.class);

		if (veiculo.getCliente() != null) {
			veiculoDTO.setClienteId(veiculo.getCliente().getIdcliente());
		}

		return ResponseEntity.ok(veiculoDTO);

	}

	@PostMapping
	public ResponseEntity<VeiculoDTO> create(@RequestBody VeiculoDTO dto) {

		Veiculo novoVeiculo = ObjectMapper.parseObject(dto, Veiculo.class);

		Cliente cliente = new Cliente();
		cliente.setIdcliente(dto.getClienteId());

		novoVeiculo.setCliente(cliente);

		Veiculo novoVeiculoSalvo = service.createVeiculo(novoVeiculo);

		VeiculoDTO novoVeiculoSalvoDTO = ObjectMapper.parseObject(novoVeiculoSalvo, VeiculoDTO.class);
		novoVeiculoSalvoDTO.setClienteId(novoVeiculoSalvo.getCliente().getIdcliente());

		return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculoSalvoDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VeiculoDTO> updateCompleto(@PathVariable Long id, @RequestBody VeiculoDTO dto) {

		Veiculo veiculo = ObjectMapper.parseObject(dto, Veiculo.class);

		if (dto.getClienteId() != null) {
			Cliente cliente = new Cliente();
			cliente.setIdcliente(dto.getClienteId());
			veiculo.setCliente(cliente);
		}
		Veiculo atualizado = service.updateVeiculo(id, veiculo);

		VeiculoDTO response = ObjectMapper.parseObject(atualizado, VeiculoDTO.class);
		response.setClienteId(atualizado.getCliente().getIdcliente());

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<VeiculoDTO> updateParcial(@PathVariable Long id, @RequestBody VeiculoDTO dto) {

		Veiculo veiculo = ObjectMapper.parseObject(dto, Veiculo.class);

		if (dto.getClienteId() != null) {
			Cliente cliente = new Cliente();
			cliente.setIdcliente(dto.getClienteId());
			veiculo.setCliente(cliente);
		}

		Veiculo atualizado = service.updateVeiculo(id, veiculo);

		VeiculoDTO response = ObjectMapper.parseObject(atualizado, VeiculoDTO.class);

		if (atualizado.getCliente() != null) {
			response.setClienteId(atualizado.getCliente().getIdcliente());
		}

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteVeiculo(id);
		return ResponseEntity.noContent().build();
	}
}
