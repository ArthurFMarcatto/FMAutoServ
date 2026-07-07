package br.com.fmautoserv.services;

import java.util.List;
import java.util.Optional;
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

	public Cliente updateCliente(Long id, Cliente cliente) {

		logger.info("Atualizando Cliente com ID: " + id);

		Cliente entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente com ID " + id + " não encontrado!"));

		Optional.ofNullable(cliente.getNome()).ifPresent(entity::setNome);
		Optional.ofNullable(cliente.getTelefone()).ifPresent(entity::setTelefone);
		Optional.ofNullable(cliente.getCelular()).ifPresent(entity::setCelular);
		Optional.ofNullable(cliente.getCpfcnpj()).ifPresent(entity::setCpfcnpj);
		Optional.ofNullable(cliente.getEndereco()).ifPresent(entity::setEndereco);
		Optional.ofNullable(cliente.getBairro()).ifPresent(entity::setBairro);
		Optional.ofNullable(cliente.getCidade()).ifPresent(entity::setCidade);

		return repository.save(entity);
	}

	public void deleteCliente(Long id) {
		logger.info("Deletando Cliente");

		Cliente entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
		repository.delete(entity);
	}
}
