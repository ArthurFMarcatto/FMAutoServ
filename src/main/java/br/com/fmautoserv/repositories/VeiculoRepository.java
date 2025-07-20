package br.com.fmautoserv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fmautoserv.models.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{

}
