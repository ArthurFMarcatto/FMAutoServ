package br.com.fmautoserv.services; // Exemplo de pacote para o serviço

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fmautoserv.exceptions.ResourceNotFoundException;
import br.com.fmautoserv.model.Cliente;
import br.com.fmautoserv.repository.ClienteRepository;

@Service
public class ClienteService {

	private Logger logger = Logger.getLogger(ClienteService.class.getName());

	@Autowired
	ClienteRepository repository;

	public List<Cliente> findAll() {
		logger.info("Buscando Todos os Clientes");
		return repository.findAll();
	}

	public Cliente findById(Long id) {
		logger.info("Buscando Cliente por ID");
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
	}

	public Cliente createCliente(Cliente cliente) {
		logger.info("Criando Novo Cliente");
		return repository.save(cliente);
	}

	public Cliente updateCliente(Cliente cliente) {
		logger.info("Atualizando Clientes");
		Cliente entity = repository.findById(cliente.getIdcliente()).orElseThrow(() -> new ResourceNotFoundException(
				"Cliente com ID " + cliente.getIdcliente() + " não encontrado para atualização!"));

		entity.setNome(cliente.getNome());
		entity.setTelefone(cliente.getTelefone());
		entity.setCelular(cliente.getCelular());
		entity.setCpfcnpj(cliente.getCpfcnpj());
		entity.setEndereco(cliente.getEndereco());
		entity.setBairro(cliente.getBairro());
		entity.setCidade(cliente.getCidade());

		return repository.save(entity);
	}

	public void deleteCliente(Long id) {
		logger.info("Deletando Cliente");

		Cliente entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
		repository.delete(entity);
	}
}
