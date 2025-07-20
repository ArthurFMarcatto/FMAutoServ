package br.com.fmautoserv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fmautoserv.models.ItemOrdem;

public interface ItemOrdemRepository extends JpaRepository<ItemOrdem, Long>{

}
