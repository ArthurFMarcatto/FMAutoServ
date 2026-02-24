package br.com.fmautoserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fmautoserv.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @EntityGraph(attributePaths = "veiculos")
    Optional<Cliente> findById(Long id);

    @EntityGraph(attributePaths = "veiculos")
    List<Cliente> findAll();
}
