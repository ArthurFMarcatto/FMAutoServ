package br.com.fmautoserv.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fmautoserv.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
