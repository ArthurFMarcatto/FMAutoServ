package br.com.fmautoserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fmautoserv.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
