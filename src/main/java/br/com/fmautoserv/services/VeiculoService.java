package br.com.fmautoserv.services; // Exemplo de pacote para o serviço

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fmautoserv.data.dto.VeiculoDTO;
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

	public Veiculo createVeiculo(VeiculoDTO dto) {
		logger.info("Criando Novo Veiculo");

		Cliente cliente = clienteRepository.findById(dto.getClienteId())
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

	public Veiculo updateVeiculo(Long id, VeiculoDTO dto) {
		logger.info("Atualizando Veiculo");

		Veiculo entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado!"));

		Cliente cliente = clienteRepository.findById(dto.getClienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

		entity.setMontadora(dto.getMontadora());
		entity.setModelo(dto.getModelo());
		entity.setAno(dto.getAno());
		entity.setCor(dto.getCor());
		entity.setPlaca(dto.getPlaca());
		entity.setCliente(cliente);

		return repository.save(entity);
	}

	public void deleteVeiculo(Long id) {
		logger.info("Deletando Veiculo");

		Veiculo entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado!"));

		repository.delete(entity);
	}
}
