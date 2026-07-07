package br.com.fmautoserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fmautoserv.model.OrdemDeServico;

public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {

	@Query("SELECT o FROM OrdemDeServico o LEFT JOIN FETCH o.itensOrdem")
	List<OrdemDeServico> findAllWithItens();

	@Query("SELECT o FROM OrdemDeServico o LEFT JOIN FETCH o.itensOrdem WHERE o.idos = :id")
	Optional<OrdemDeServico> findByIdWithItens(@Param("id") Long id);
}
