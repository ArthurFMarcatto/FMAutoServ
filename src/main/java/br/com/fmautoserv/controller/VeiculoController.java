package br.com.fmautoserv.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.fmautoserv.data.dto.VeiculoDTO;
import br.com.fmautoserv.model.Veiculo;
import br.com.fmautoserv.services.VeiculoService;

@RestController
@RequestMapping("api/veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoService service;

	@GetMapping
	public ResponseEntity<List<VeiculoDTO>> findAll() {

		List<VeiculoDTO> dtos = service.findAll().stream().map(this::convertToDTO).toList();

		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<VeiculoDTO> findById(@PathVariable Long id) {

		Veiculo veiculo = service.findById(id);

		return ResponseEntity.ok(convertToDTO(veiculo));
	}

	@PostMapping
	public ResponseEntity<VeiculoDTO> create(@RequestBody VeiculoDTO dto) {

		Veiculo veiculoSalvo = service.createVeiculo(dto);

		return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(veiculoSalvo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<VeiculoDTO> update(@PathVariable Long id, @RequestBody VeiculoDTO dto) {

		Veiculo atualizado = service.updateVeiculo(id, dto);

		return ResponseEntity.ok(convertToDTO(atualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteVeiculo(id);
		return ResponseEntity.noContent().build();
	}

	private VeiculoDTO convertToDTO(Veiculo veiculo) {

		VeiculoDTO dto = new VeiculoDTO();
		dto.setIdveiculo(veiculo.getIdveiculo());
		dto.setMontadora(veiculo.getMontadora());
		dto.setModelo(veiculo.getModelo());
		dto.setAno(veiculo.getAno());
		dto.setCor(veiculo.getCor());
		dto.setPlaca(veiculo.getPlaca());
		dto.setClienteId(veiculo.getCliente().getIdcliente());

		return dto;
	}
}
