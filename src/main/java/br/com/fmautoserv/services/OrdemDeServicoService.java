package br.com.fmautoserv.services; 

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fmautoserv.data.dto.ItemOrdemDTO;
import br.com.fmautoserv.data.dto.OrdemDeServicoDTO;
import br.com.fmautoserv.exceptions.ResourceNotFoundException;
import br.com.fmautoserv.model.Cliente;
import br.com.fmautoserv.model.ItemOrdem;
import br.com.fmautoserv.model.OrdemDeServico;
import br.com.fmautoserv.model.Veiculo;
import br.com.fmautoserv.repository.ClienteRepository;
import br.com.fmautoserv.repository.OrdemDeServicoRepository;
import br.com.fmautoserv.repository.VeiculoRepository;
import jakarta.transaction.Transactional;

@Service
public class OrdemDeServicoService {

	private Logger logger = Logger.getLogger(OrdemDeServicoService.class.getName());

	@Autowired
	private OrdemDeServicoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	public List<OrdemDeServico> findAll() {
		logger.info("Buscando Todos as Ordens de Serviço");
		return repository.findAllWithItens();
	}

	public OrdemDeServico findById(Long id) {
		logger.info("Buscando Ordem de Serviço por ID");
		return repository.findByIdWithItens(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada!"));
	}

	public OrdemDeServico createOrdemDeServico(OrdemDeServicoDTO dto) {
		logger.info("Criando Nova Ordem de Serviço");

		Cliente cliente = clienteRepository.findById(dto.getClienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));

		Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encontrado!"));

		OrdemDeServico ordem = new OrdemDeServico();
		ordem.setCliente(cliente);
		ordem.setVeiculo(veiculo);

		for (ItemOrdemDTO dtoItem : dto.getItensOrdem()) {
			ItemOrdem item = new ItemOrdem();
			item.setDescricao(dtoItem.getDescricao());
			item.setQuantidade(dtoItem.getQuantidade());
			item.setValorUnitario(dtoItem.getValorUnitario());

			ordem.adicionarItem(item);
		}

		return repository.save(ordem);
	}

	public OrdemDeServico updateOrdemServico(Long id, OrdemDeServicoDTO dto) {
		logger.info("Atualizando Ordem de Serviço");

		OrdemDeServico entity = repository.findByIdWithItens(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ordem de Serviço não encontrada!"));

		if (dto.getClienteId() != null) {
			Cliente cliente = clienteRepository.findById(dto.getClienteId())
					.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
			entity.setCliente(cliente);
		}

		if (dto.getVeiculoId() != null) {
			Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
					.orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado!"));
			entity.setVeiculo(veiculo);
		}
		
		if (dto.getItensOrdem() != null) {
		    entity.getItensOrdem().clear();
		    for (ItemOrdemDTO dtoItem : dto.getItensOrdem()) {
		        ItemOrdem item = new ItemOrdem();
		        item.setDescricao(dtoItem.getDescricao());
		        item.setQuantidade(dtoItem.getQuantidade());
		        item.setValorUnitario(dtoItem.getValorUnitario());
		        entity.adicionarItem(item);
		    }
		}

		return repository.save(entity);
	}

	public void deleteOrdemDeServico(Long id) {
		logger.info("Deletando Ordem De Servico");

		OrdemDeServico entity = repository.findByIdWithItens(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ordem De Servico não encontrado!"));

		repository.delete(entity);
	}

}
