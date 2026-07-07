package br.com.fmautoserv.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fmautoserv.exceptions.ResourceNotFoundException;
import br.com.fmautoserv.model.Cliente;
import br.com.fmautoserv.model.Veiculo;
import br.com.fmautoserv.repository.ClienteRepository;
import br.com.fmautoserv.repository.VeiculoRepository;

@Service
public class VeiculoService {

	private Logger logger = Logger.getLogger(VeiculoService.class.getName());

	@Autowired
	private VeiculoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Veiculo> findAll() {
		logger.info("Buscando Todos os Veiculos");
		return repository.findAll();
	}

	public Veiculo findById(Long id) {
		logger.info("Buscando Veiculo por ID");
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado!"));
	}

	public Veiculo createVeiculo(Veiculo dto) {
		logger.info("Criando Novo Veiculo");

		Cliente cliente = clienteRepository.findById(dto.getCliente().getIdcliente())
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

		Veiculo veiculo = new Veiculo();
		veiculo.setMontadora(dto.getMontadora());
		veiculo.setModelo(dto.getModelo());
		veiculo.setAno(dto.getAno());
		veiculo.setCor(dto.getCor());
		veiculo.setPlaca(dto.getPlaca());
		veiculo.setCliente(cliente);

		return repository.save(veiculo);
	}

	public Veiculo updateVeiculo(Long id, Veiculo dto) {
	    logger.info("Atualizando Veiculo");

	    Veiculo entity = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado!"));

	    if (dto.getMontadora() != null) {
	        entity.setMontadora(dto.getMontadora());
	    }

	    if (dto.getModelo() != null) {
	        entity.setModelo(dto.getModelo());
	    }

	    if (dto.getAno() != 0) {
	        entity.setAno(dto.getAno());
	    }

	    if (dto.getCor() != null) {
	        entity.setCor(dto.getCor());
	    }

	    if (dto.getPlaca() != null) {
	        entity.setPlaca(dto.getPlaca());
	    }

	    if (dto.getCliente() != null && dto.getCliente().getIdcliente() != null) {

	        Cliente cliente = clienteRepository.findById(dto.getCliente().getIdcliente())
	                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

	        entity.setCliente(cliente);
	    }

	    return repository.save(entity);
	}

	public void deleteVeiculo(Long id) {
		logger.info("Deletando Veiculo");

		Veiculo entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado!"));

		repository.delete(entity);
	}
}
