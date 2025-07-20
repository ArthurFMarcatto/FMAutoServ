package br.com.fmautoserv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fmautoserv.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
