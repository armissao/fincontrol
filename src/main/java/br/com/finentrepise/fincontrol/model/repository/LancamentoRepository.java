package br.com.finentrepise.fincontrol.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.finentrepise.fincontrol.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
